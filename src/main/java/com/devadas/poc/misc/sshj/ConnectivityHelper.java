package com.devadas.poc.misc.sshj;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.StreamGobbler;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

public class ConnectivityHelper {

	private static final Logger log = LoggerFactory.getLogger(ConnectivityHelper.class);

	public static final int SSH_TIMEOUT = 60000; // in ms
	public static final int CMD_WAIT_TIMEOUT = 30000; // in ms
	public static final int WGET_TIMEOUT = 10; // in seconds

	private SSHClient client = null;
	private Session sess = null;

	public ConnectivityHelper(String ip, String user, String pw) throws IOException {
		this(ip, user, pw, null);
	}

	public ConnectivityHelper(String ip, String user, String pw, Integer port) throws IOException {
		log.debug("ssh {} {} {} {}", ip, user, (pw != null) ? "xxx" : null, port);
		client = new SSHClient();
		client.addHostKeyVerifier(new PromiscuousVerifier());
		client.setConnectTimeout(SSH_TIMEOUT);
		if (port != null) {
			client.connect(ip, port);
		} else {
			client.connect(ip);
		}
		client.authPassword(user, pw);
		sess = client.startSession();
	}

	public void execute(String cmd, boolean wait) throws IOException {
		log.debug("Ready to execute {}", cmd);
		Command command = sess.exec(cmd);
		if (wait) {
			command.join();
		} else {
			command.join(CMD_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
		}
	}

	public SSHCMDResponse execute(String cmd) throws IOException {
		SSHCMDResponse cmdResponse = new SSHCMDResponse();
		Command command = sess.exec(cmd);
		try {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			String output = executor.submit(() -> IOUtils.readFully(command.getInputStream()).toString()).get();
			String err = executor.submit(() -> IOUtils.readFully(command.getErrorStream()).toString()).get();
			System.out.println("Output = " + output + ", error = " + err);
		} catch (InterruptedException | ExecutionException e) {
			throw new IOException(e);
		}
//		cmdResponse.setStdOUT(IOUtils.readFully(command.getInputStream()).toString());
//		cmdResponse.setStdERR(IOUtils.readFully(command.getErrorStream()).toString());
		command.join();
		command.close();
		cmdResponse.setExitStatus(command.getExitStatus());
		return cmdResponse;
	}

	public void execute(ConnectivityTest test, ConnectivityTestResult result, String cmd, boolean wait)
			throws IOException {
		Command command = sess.exec(cmd);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(command.getInputStream()))) {
			String line;
			StringBuilder output = new StringBuilder();
			do {
				line = reader.readLine();
				if (line != null)
					output.append(line + "\n");
				if (!wait && result.parseInProgressResult(test, line) != null) {
					break;
				}
			} while (line != null);

			if (wait) {
				command.join();
			} else {
				command.join(CMD_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
			}
			result.parseResult(test, output.toString(), command.getExitStatus());
		}
	}

	// Always include call to close() in a finally block
	public void close() {
		try {
			if (sess != null) {
				sess.close();
			}
			if (client != null) {
				client.close();
			}
		} catch (IOException ignore) {
		}
	}

	/**
	 * TODO: Many parameters are not used. Check. How about mode? 
	 */
	public static void scpHelper(String ip, String user, String pw, File file, String destFolder, String mode,
			String remoteFileName) throws IOException {
		log.debug("scp {} {} {} {} {}", ip, user, (pw != null) ? "xxx" : null, destFolder, mode);
		SSHClient client = null;
		try {
			client = new SSHClient();
			client.addHostKeyVerifier(new PromiscuousVerifier());
			client.setConnectTimeout(SSH_TIMEOUT);
			client.connect(ip);
			client.authPassword(user, pw);

			if (!client.isAuthenticated()) {
				// TODO************** Uncomment
				// throw new GeneralServiceException("Authentication failed");
			}
			String remotePath = destFolder + "/" + remoteFileName;
			FileSystemFile sourceFile = new FileSystemFile(file) {
				@Override
				public int getPermissions() throws IOException {
					return Integer.parseInt(mode);
				}
			};
			client.newSCPFileTransfer().upload(sourceFile, remotePath);
			System.out.println("SCP Done with mode " + mode);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	/**
	 * THIS METHOD IS TO BE REPLACED AT ConnectivityRestHelper.java
	 */
	
    public static final String CONNECTIVITY_FILES_DIR = "C:\\Users\\dtv";
    public static final String RESULT_FILE_LOCATION = CONNECTIVITY_FILES_DIR + "/results";
    public static final String INPUT_FILE_LOCATION = CONNECTIVITY_FILES_DIR + "\\inputs";
    
	public static void saveFileContentFromRemote(String ipAddress, String sshUserName, String sshPassword,
			String directory, String fileName, long systemID) {
		SSHClient client = null;
		try {
			client = new SSHClient();
			client.addHostKeyVerifier(new PromiscuousVerifier());
			client.setConnectTimeout(SSH_TIMEOUT);
			client.connect(ipAddress);
			client.authPassword(sshUserName, sshPassword);

			if (!client.isAuthenticated()) {
				// TODO************** Uncomment
				// throw new IllegalArgumentException("Login to remote system failed.Failed to create System..");
			}
			String remotePath = directory + "/" + fileName;
			String localPath = ConnectivityHelper.INPUT_FILE_LOCATION + "/internal_input_" + systemID;
			client.newSCPFileTransfer().download(remotePath, localPath);
			System.out.println("Download complete");
		} catch (Exception e) {
			 //log.error("Unable to load items from remote file.Reason :{}", ex);
//            throw new GeneralServiceException(ex);
		}
		finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException ignore) {
				}
			}
		}
    }
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
