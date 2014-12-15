package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the device_list database table.
 * 
 */
public class DeviceList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer  id;

	private Date createdAt;

	private Integer  createdBy;

	private Integer  deviceId;

	private Integer  iconRule;

	private String ipAddr;//远程机器名
	private String remoteAddr;//远程IP地址

	private String logicNo;

	private String name;

	private String physicalNo;

	private Integer  spotId;

	private byte status;

	private Date updatedAt;

	private Integer  updatedBy;
	
	private Integer deviceType; //机器类型(0,增值机，1主机)
	
	private String drvUsername;
	private String drvPwd;
	private String remotePort;//

	public DeviceList() {
	}

	public Integer  getId() {
		return this.id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer  getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer  createdBy) {
		this.createdBy = createdBy;
	}

	public Integer  getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer  deviceId) {
		this.deviceId = deviceId;
	}

	public Integer  getIconRule() {
		return this.iconRule;
	}

	public void setIconRule(Integer  iconRule) {
		this.iconRule = iconRule;
	}

	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getLogicNo() {
		return this.logicNo;
	}

	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhysicalNo() {
		return this.physicalNo;
	}

	public void setPhysicalNo(String physicalNo) {
		this.physicalNo = physicalNo;
	}

	public Integer  getSpotId() {
		return this.spotId;
	}

	public void setSpotId(Integer  spotId) {
		this.spotId = spotId;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer  getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer  updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
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

}