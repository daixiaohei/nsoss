package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class UserRoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String roleId;

	private String userId;

	private String roleName;

	private String userName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
