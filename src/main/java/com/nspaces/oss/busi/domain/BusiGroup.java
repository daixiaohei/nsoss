package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the busi_group database table.
 * 
 */
public class BusiGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String createdAt;

	private Integer createdBy;

	private String name;

	private String updatedAt;

	private Integer updatedBy;

	public BusiGroup() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}