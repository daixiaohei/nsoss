package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the device_list_cache database table.
 * 
 */
public class DeviceListCache implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int deviceListId;

	private String dyncKey;

	private int dyncSeq;

	private String partNo;

	private String shId;

	public DeviceListCache() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeviceListId() {
		return this.deviceListId;
	}

	public void setDeviceListId(int deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getDyncKey() {
		return this.dyncKey;
	}

	public void setDyncKey(String dyncKey) {
		this.dyncKey = dyncKey;
	}

	public int getDyncSeq() {
		return this.dyncSeq;
	}

	public void setDyncSeq(int dyncSeq) {
		this.dyncSeq = dyncSeq;
	}

	public String getPartNo() {
		return this.partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getShId() {
		return this.shId;
	}

	public void setShId(String shId) {
		this.shId = shId;
	}

}