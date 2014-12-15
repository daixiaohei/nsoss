package com.nspaces.oss.busi.dto;

public class PartListDTO {

	private Integer id;
	private Integer partId;
	private String partNo;
	private String partName;
	private Integer partType;//0增值设备，1感应设备，2视频设备
	private String busiCode;
	private Integer deviceListId;
	private String logicNo;
	private String physicalNo;
	private String portNum;//端口号
	private String partNameNick;//设备别名
	private String transNo;
	private Short status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Integer getPartType() {
		return partType;
	}
	public void setPartType(Integer partType) {
		this.partType = partType;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public Integer getDeviceListId() {
		return deviceListId;
	}
	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}
	public String getLogicNo() {
		return logicNo;
	}
	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
	public String getPhysicalNo() {
		return physicalNo;
	}
	public void setPhysicalNo(String physicalNo) {
		this.physicalNo = physicalNo;
	}
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	public String getPartNameNick() {
		return partNameNick;
	}
	public void setPartNameNick(String partNameNick) {
		this.partNameNick = partNameNick;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	

}
