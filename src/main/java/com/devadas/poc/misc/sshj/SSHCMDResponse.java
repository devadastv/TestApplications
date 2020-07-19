package com.devadas.poc.misc.sshj;

public class SSHCMDResponse {

	private Integer exitStatus;
	private String stdOUT;
	private String stdERR;

	public Integer getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(Integer exitStatus) {
		this.exitStatus = exitStatus;
	}

	public String getStdOUT() {
		return stdOUT;
	}

	public void setStdOUT(String stdOUT) {
		this.stdOUT = stdOUT;
	}

	public String getStdERR() {
		return stdERR;
	}

	public void setStdERR(String stdERR) {
		this.stdERR = stdERR;
	}

	@Override
	public String toString() {
		return "SSHCMDResponse [exitStatus=" + exitStatus + ", stdOUT=" + stdOUT + ", stdERR=" + stdERR + "]";
	}

	
}