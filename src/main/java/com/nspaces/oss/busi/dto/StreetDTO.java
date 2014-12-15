package com.nspaces.oss.busi.dto;

public class StreetDTO {

	private int id;

	private String address;

	private String cityNo;

	private String name;

	private String nameFull;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFull() {
		return nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

	@Override
	public String toString() {
		return "StreetDTO [id=" + id + ", address=" + address + ", cityNo="
				+ cityNo + ", name=" + name + ", nameFull=" + nameFull + "]";
	}
	
	
}
