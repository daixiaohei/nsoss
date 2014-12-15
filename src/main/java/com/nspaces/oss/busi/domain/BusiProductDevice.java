package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the busi_product_device database table.
 * 
 */
public class BusiProductDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int busiId;

	private int deviceListId;

	private int prdtId;

	public BusiProductDevice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public int getDeviceListId() {
		return this.deviceListId;
	}

	public void setDeviceListId(int deviceListId) {
		this.deviceListId = deviceListId;
	}

	public int getPrdtId() {
		return this.prdtId;
	}

	public void setPrdtId(int prdtId) {
		this.prdtId = prdtId;
	}

}