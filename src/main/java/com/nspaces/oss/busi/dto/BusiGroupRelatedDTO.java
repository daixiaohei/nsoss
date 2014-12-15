package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class BusiGroupRelatedDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String busiGroupId;

	private String busiId;

	private String busiGroupName;

	private String busiName;

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


	public String getBusiGroupName() {
		return busiGroupName;
	}

	public void setBusiGroupName(String busiGroupName) {
		this.busiGroupName = busiGroupName;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

}
