package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the street database table.
 * 
 */
public class Street implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String address;

	private String cityNo;

	private String name;

	private String nameFull;

	public Street() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFull() {
		return this.nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

}