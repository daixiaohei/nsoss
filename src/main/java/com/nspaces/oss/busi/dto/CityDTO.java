package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class CityDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	private String cityNo;

	private String cityName;

	private int level;

	private String parentNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	
	

}
