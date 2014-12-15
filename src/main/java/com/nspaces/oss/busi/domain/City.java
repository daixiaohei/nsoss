package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the city database table.
 * 
 */
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String cityNo;

	private String cityName;

	private int level;

	private String parentNo;

	public City() {
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentNo() {
		return this.parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

}