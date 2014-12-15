package com.nspaces.oss.busi.dto.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class PartDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7942550555089298812L;
	
	private String partNo;//设备编号
	private Integer partId;//设备主键
	private String partName;//设备名称
	private String portNum;//设备Io地址
	private String value1Name;//当为1时，表示的值
	private String value0Name;//当为0时，表示的值
	private Integer curValue;//当前值
	private String curValueName;//当前的设备名称
	private Integer partType;// 设备类型，1增值设备，2输入设备，3视频设备，4输出设备
	
	private Integer timeSet;//是否需要设置时间
	private String vbAddr;//是否需要设置时间
	
	private Integer level;//设备级别
	private Integer push;//推送状态
	
	private String startTime; //开始时间
	private String endTime;//结束时间
	
	private Integer timeId;//TimeID
	
	
	@XmlElement
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	@XmlElement
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	
	@XmlElement
	public Integer getPartId() {
		return partId;
	}
	
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	
	@XmlElement
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	public String getValue1Name() {
		return value1Name;
	}
	public void setValue1Name(String value1Name) {
		this.value1Name = value1Name;
	}
	public String getValue0Name() {
		return value0Name;
	}
	public void setValue0Name(String value0Name) {
		this.value0Name = value0Name;
	}
	public Integer getCurValue() {
		return curValue;
	}
	public void setCurValue(Integer curValue) {
		this.curValue = curValue;
	}
	public String getCurValueName() {
		return curValueName;
	}
	public void setCurValueName(String curValueName) {
		this.curValueName = curValueName;
	}
	public Integer getPartType() {
		return partType;
	}
	public void setPartType(Integer partType) {
		this.partType = partType;
	}
	public Integer getTimeSet() {
		return timeSet;
	}
	public void setTimeSet(Integer timeSet) {
		this.timeSet = timeSet;
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
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
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
