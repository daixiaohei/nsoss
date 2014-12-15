package com.nspaces.oss.mcs.domain;

public class PortData {
	private String portNum;//I/O端口号
	private int portValue;//收到的值
	
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	public int getPortValue() {
		return portValue;
	}
	public void setPortValue(int portValue) {
		this.portValue = portValue;
	}
}
