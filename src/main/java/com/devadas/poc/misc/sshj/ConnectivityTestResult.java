package com.devadas.poc.misc.sshj;

public class ConnectivityTestResult {

	public Object parseInProgressResult(ConnectivityTest test, String line) {
		System.out.println("==== line = " + line);
		return null;
	}

	public void parseResult(ConnectivityTest test, String string, Integer exitStatus) {
		System.out.println("output = " + string + ", exitstatus = " + exitStatus);
		
	}

}
