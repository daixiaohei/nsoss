package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the device database table.
 * 
 */
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Date createdAt;

	private int createdBy;

	private String deviceNo;

	private int deviceType;

	private byte logicType;

	private String memo;

	private String name;

	private Date updatedAt;

	private int updatedBy;

	private String vender;

	public Device() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public int getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
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

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getVender() {
		return this.vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

}