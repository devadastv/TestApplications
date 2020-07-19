package com.devadas.poc.misc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.verification.OpenSSHKnownHosts;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public class SSHj_Tester {

	public static void main(String[] args) throws IOException {
		SSHj_Tester tester = new SSHj_Tester();
		tester.connect();
	}

	public void connect() {
		SSHClient client = null;
		long timeBefore = 0;
		try {
			client = new SSHClient();
			client.addHostKeyVerifier(new PromiscuousVerifier());
			client.connect("10.65.16.233");
			client.authPassword("setup", "May@2020!");
			Session session = null;
			try {
				System.out.println("starting..." );
				timeBefore = System.currentTimeMillis();
				session = client.startSession();
				session.allocateDefaultPTY();
				final Command cmd = session.exec("sleep 40; echo hello"); //ls /home/setup
				System.out.println("After exec..." + (System.currentTimeMillis() - timeBefore)/1000);
                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                System.out.println("Going to wait..."  + (System.currentTimeMillis() - timeBefore)/1000);
				try {
	                cmd.join(35, TimeUnit.SECONDS);
				} catch (Exception e) {
					System.out.println("-------Exception caught at "  + (System.currentTimeMillis() - timeBefore)/1000);
					e.printStackTrace();
				}
				System.out.println("After wait..."  + (System.currentTimeMillis() - timeBefore)/1000);
//                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                System.out.println("\n** exit status: " + cmd.getExitStatus() 
                + "time " +  (System.currentTimeMillis() - timeBefore)/1000);

			} finally {
				session.close();
			}
		} catch (Throwable e) {
			System.out.println("Exception caught at "  + (System.currentTimeMillis() - timeBefore)/1000);
			e.printStackTrace();
		}
		finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
