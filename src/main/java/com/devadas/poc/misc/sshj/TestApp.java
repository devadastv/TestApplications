package com.devadas.poc.misc.sshj;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.common.StreamCopier;

public class TestApp {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {
//		int a = 0555; // same as decimal 365
//		System.out.println("a = " + a);
//		System.out.println("--" + Integer.toOctalString(555));
//		System.out.println(Integer.toOctalString(365 & 07777));
		int decimal=Integer.parseInt("555" ,8);
		System.out.println(decimal);
		String mode = "555";
		String modeStr = "0" + mode;
		System.out.println("::" + Integer.parseInt(modeStr));
		System.out.println(Integer.toOctalString(Integer.parseInt(modeStr)));
		System.out.println(Integer.parseInt(Integer.toOctalString(Integer.parseInt(modeStr))));
		Collections.synchronizedMap(null);
		
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		System.out.println("Starting thread at " + System.currentTimeMillis()/1000);
//		String output = executor.submit(() -> {
//			Thread.sleep(5000);
//			return "Done";
//			}).get(2000, TimeUnit.MILLISECONDS);
//		System.out.println("Output = " + output);
//		System.out.println("Ending thread at " + System.currentTimeMillis()/1000);
		
//		ConnectivityHelper.saveFileContentFromRemote("10.65.16.233", "setup", "May@2020!", "/home/setup/devadas", "heapcollector.sh", 123);
//		StreamCopier c;
		
//		File file = new File("C:\\Users\\dtv\\testfile.txt");
//		System.out.println(file.isFile());
//		ConnectivityHelper.scpHelper("10.65.16.233", "setup", "May@2020!", file, "/tmp/", "0555", "hellooooo");
//		
//		ConnectivityHelper helper = new ConnectivityHelper("10.65.16.233", "setup", "May@2020!");
//		SSHCMDResponse response = helper.execute("lss /home/setup/devadas");
//		System.out.println("Response: " + response);
//		helper.close();
		
//		ConnectivityHelper helper = new ConnectivityHelper("10.65.16.233", "setup", "May@2020!");
//		helper.execute(new ConnectivityTest(), 
//				new ConnectivityTestResult(), "lss /home/setup/devadas", true);
//		helper.close();
//		
//		helper = new ConnectivityHelper("10.65.16.233", "setup", "May@2020!");
//		helper.execute("ls /home/setup/devadas", true);
//		System.out.println("Done");
//		
//		helper = new ConnectivityHelper("10.65.16.233", "setup", "May@2020!");
//		helper.execute("ls /home/setup/devadas", false);
//		System.out.println("Done");
	}

}
