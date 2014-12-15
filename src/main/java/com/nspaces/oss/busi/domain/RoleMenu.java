package com.nspaces.oss.busi.domain;

import java.io.Serializable;

/**
 * The persistent class for the role_menu database table.
 * 
 */
public class RoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer menuId;

	private Integer roleId;

	public RoleMenu() {
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}