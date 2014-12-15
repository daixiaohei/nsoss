package com.nspaces.oss.busi.domain;

import java.io.Serializable;

/**
 * The persistent class for the busi_group_related database table.
 * 
 */
public class BusiGroupRelated implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer busiGroupId;

	private Integer busiId;

	public BusiGroupRelated() {
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

	public Integer getBusiId() {
		return busiId;
	}

	public void setBusiId(Integer busiId) {
		this.busiId = busiId;
	}

}