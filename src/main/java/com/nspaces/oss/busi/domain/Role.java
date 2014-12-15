package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the role database table.
 * 
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	private Integer id;

	private String name;

	public Role() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}