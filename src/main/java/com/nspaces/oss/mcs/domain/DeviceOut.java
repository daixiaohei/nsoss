package com.nspaces.oss.mcs.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nspaces.oss.base.mongo.MongoBase;

@Document(collection="device_out")
public class DeviceOut extends MongoBase{
    
	private Integer deviceListId;
	private Date createdAt;
	private String partNo;//设备编号
    private String portNum;//I/O端口号
    private String partName;//设备名称
	private Integer portValue;//收到的值
	
	private String startTime;//开始时间时间+分 1000

	private String endTime;//结束时间
	
	public Integer getDeviceListId() {
		return deviceListId;
	}
	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Integer getPortValue() {
		return portValue;
	}
	public void setPortValue(Integer portValue) {
		this.portValue = portValue;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
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
}
