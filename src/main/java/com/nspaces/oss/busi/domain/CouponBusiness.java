package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the coupon_business database table.
 * 
 */
public class CouponBusiness implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private int busiId;

	private int couponId;

	public CouponBusiness() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

}