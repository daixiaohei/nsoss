package com.nspaces.oss.busi.dto;

import java.util.List;

import com.nspaces.oss.busi.domain.DeviceList;

public class DeviceDrvDTO {

	private String remoteAddr;
	private String name;
	private String ipAddr;
	private String drvUsername;
	private String drvPwd;
	private String id;
	private String remotePort;

	private List<PartDrvDTO> partDrvs;// 设备明细

	public List<PartDrvDTO> getPartDrvs() {
		return partDrvs;
	}

	public void setPartDrvs(List<PartDrvDTO> partDrvs) {
		this.partDrvs = partDrvs;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getDrvUsername() {
		return drvUsername;
	}

	public void setDrvUsername(String drvUsername) {
		this.drvUsername = drvUsername;
	}

	public String getDrvPwd() {
		return drvPwd;
	}

	public void setDrvPwd(String drvPwd) {
		this.drvPwd = drvPwd;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

}
