package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the spot database table.
 * 
 */
public class Spot implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String address;

	private String cityNo;

	private Date createdAt;

	private int createdBy;

	private short deviceNum;

	private Date endDate;

	private float latitude;

	private float longitude;

	private String memo;

	private String name;

	private String postcode;

	private short roomNum;

	private String spotNo;

	private Date startDate;

	private byte status;

	private int streetId;

	private Date updatedAt;

	private int updatedBy;

	public Spot() {
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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public short getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(short deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public short getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(short roomNum) {
		this.roomNum = roomNum;
	}

	public String getSpotNo() {
		return this.spotNo;
	}

	public void setSpotNo(String spotNo) {
		this.spotNo = spotNo;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getStreetId() {
		return this.streetId;
	}

	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Spot [id=" + id + ", address=" + address + ", cityNo=" + cityNo
				+ ", createdAt=" + createdAt + ", createdBy=" + createdBy
				+ ", deviceNum=" + deviceNum + ", endDate=" + endDate
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", memo=" + memo + ", name=" + name + ", postcode="
				+ postcode + ", roomNum=" + roomNum + ", spotNo=" + spotNo
				+ ", startDate=" + startDate + ", status=" + status
				+ ", streetId=" + streetId + ", updatedAt=" + updatedAt
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	

}