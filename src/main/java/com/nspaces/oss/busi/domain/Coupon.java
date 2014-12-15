package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the coupon database table.
 * 
 */
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer coopId;
	
	private String couponType;
	
	private String bindType;
	
	private String bindBank;

	private Date createdAt;

	private String createdBy;

	private String endDate;

	private String memo;

	private String name;

	private String startDate;

	private String status;

	private Date updatedAt;

	private String updatedBy;

	private String url;

	public Coupon() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoopId() {
		return this.coopId;
	}

	public void setCoopId(Integer coopId) {
		this.coopId = coopId;
	}

	public String getCouponType() {
		return this.couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	
	public String getBindBank() {
		return bindBank;
	}

	public void setBindBank(String bindBank) {
		this.bindBank = bindBank;
	}
}