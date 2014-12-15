package com.nspaces.oss.busi.dto;

import java.util.Date;

public class DeviceListDTO {

	
	private Integer  id;


	private Integer  deviceId;

	private Integer  iconRule;

	private String ipAddr;//远程机器名
	private String remoteAddr;//远程IP地址

	private String logicNo;

	private String name;

	private String physicalNo;

	private Integer  spotId;

	private byte status;
	
	private Integer deviceType; //机器类型(0,增值机，1主机)
	
	private String drvUsername;
	private String drvPwd;
	private String remotePort;//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getIconRule() {
		return iconRule;
	}
	public void setIconRule(Integer iconRule) {
		this.iconRule = iconRule;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getLogicNo() {
		return logicNo;
	}
	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhysicalNo() {
		return physicalNo;
	}
	public void setPhysicalNo(String physicalNo) {
		this.physicalNo = physicalNo;
	}
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
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
	public String getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}
	@Override
	public String toString() {
		return "DeviceListDTO [id=" + id + ", deviceId=" + deviceId
				+ ", iconRule=" + iconRule + ", ipAddr=" + ipAddr
				+ ", remoteAddr=" + remoteAddr + ", logicNo=" + logicNo
				+ ", name=" + name + ", physicalNo=" + physicalNo + ", spotId="
				+ spotId + ", status=" + status + ", deviceType=" + deviceType
				+ ", drvUsername=" + drvUsername + ", drvPwd=" + drvPwd
				+ ", remotePort=" + remotePort + "]";
	}
	
	
	
	
	
}
