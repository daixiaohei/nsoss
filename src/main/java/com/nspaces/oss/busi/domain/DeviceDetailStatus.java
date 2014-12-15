package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the device_detail_status database table.
 * 
 */
public class DeviceDetailStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Date createdAt;

	private int createdBy;

	private int deviceListId;

	private String partNo;

	private int status;

	private int statusMaint;

	private Date updatedAt;

	private int updatedBy;

	public DeviceDetailStatus() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public int getDeviceListId() {
		return this.deviceListId;
	}

	public void setDeviceListId(int deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getPartNo() {
		return this.partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatusMaint() {
		return this.statusMaint;
	}

	public void setStatusMaint(int statusMaint) {
		this.statusMaint = statusMaint;
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

}