package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the busi_group_device database table.
 * 
 */
public class BusiGroupDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer busiGroupId;

	private Integer deviceListId;

	public BusiGroupDevice() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusiGroupId() {
		return busiGroupId;
	}

	public void setBusiGroupId(Integer busiGroupId) {
		this.busiGroupId = busiGroupId;
	}

	public Integer getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	
}