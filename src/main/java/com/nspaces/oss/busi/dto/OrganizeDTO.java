package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class OrganizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	private String name;

	private String manager;

	private Integer parentId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	

}
