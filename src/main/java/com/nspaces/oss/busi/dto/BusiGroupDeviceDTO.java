package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class BusiGroupDeviceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String busiGroupId;

	private String deviceListId;

	private String busiGroupName;

	private String deviceListName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiGroupId() {
		return busiGroupId;
	}

	public void setBusiGroupId(String busiGroupId) {
		this.busiGroupId = busiGroupId;
	}

	public String getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(String deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getBusiGroupName() {
		return busiGroupName;
	}

	public void setBusiGroupName(String busiGroupName) {
		this.busiGroupName = busiGroupName;
	}

	public String getDeviceListName() {
		return deviceListName;
	}

	public void setDeviceListName(String deviceListName) {
		this.deviceListName = deviceListName;
	}

	

}
