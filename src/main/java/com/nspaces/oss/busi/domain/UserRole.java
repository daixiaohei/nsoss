package com.nspaces.oss.busi.domain;

import java.io.Serializable;

/**
 * The persistent class for the user_role database table.
 * 
 */
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer roleId;

	private Integer userId;

	public UserRole() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}