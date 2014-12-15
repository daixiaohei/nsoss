package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the part database table.
 * 
 */
public class Part implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date createdAt;

	private Integer createdBy;

	private Integer deviceId;

	private byte logicType;

	private String memo;

	private String name;

	private String partNo;

	private byte partType;//0增值设备，1感应设备，2视频设备

	private Date updatedAt;

	private Integer updatedBy;

	private String vender;
	
	private Integer timeSet;//是否需要设置时间

	public Part() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public byte getLogicType() {
		return this.logicType;
	}

	public void setLogicType(byte logicType) {
		this.logicType = logicType;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNo() {
		return this.partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public byte getPartType() {
		return this.partType;
	}

	public void setPartType(byte partType) {
		this.partType = partType;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getVender() {
		return this.vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

	public Integer getTimeSet() {
		return timeSet;
	}

	public void setTimeSet(Integer timeSet) {
		this.timeSet = timeSet;
	}

}