package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the coupon_business database table.
 * 
 */
public class CouponDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private int deviceListId;

	private int couponId;

	public CouponDevice() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(int deviceListId) {
		this.deviceListId = deviceListId;
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

}