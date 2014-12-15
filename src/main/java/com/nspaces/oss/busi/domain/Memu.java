package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the memu database table.
 * 
 */
public class Memu implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String picUrl;

	private String url;
	
	private Integer type;

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Memu() {
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

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}