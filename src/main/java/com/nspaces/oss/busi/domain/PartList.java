package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the part_list database table.
 * 
 */
public class PartList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String busiCode;

	private Integer deviceListId;

	private String logicNo;

	private Integer partId;

	private String physicalNo;

	private String portNum;

	private Short status;

	private String transNo;
	
	private String partNameNick;
	
	private String startTime; //PLC 控制的时间开始
	
	private String endTime;//PLC控制的时间结束
	
	private String vbAddr;//VB寄存器地址
	
	private Integer timeId;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public PartList() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusiCode() {
		return this.busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public Integer getDeviceListId() {
		return this.deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getLogicNo() {
		return this.logicNo;
	}

	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}

	public Integer getPartId() {
		return this.partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public String getPhysicalNo() {
		return this.physicalNo;
	}

	public void setPhysicalNo(String physicalNo) {
		this.physicalNo = physicalNo;
	}

	public String getPortNum() {
		return this.portNum;
	}

	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getTransNo() {
		return this.transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getPartNameNick() {
		return partNameNick;
	}

	public void setPartNameNick(String partNameNick) {
		this.partNameNick = partNameNick;
	}

	public String getVbAddr() {
		return vbAddr;
	}

	public void setVbAddr(String vbAddr) {
		this.vbAddr = vbAddr;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	
}