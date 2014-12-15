package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * The persistent class for the busi_record_coupon database table.
 * 
 */
public class BusiRecordCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private BigInteger busiRecordId;

	private String couponCode;

	private int couponDetailId;

	public BusiRecordCoupon() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getBusiRecordId() {
		return this.busiRecordId;
	}

	public void setBusiRecordId(BigInteger busiRecordId) {
		this.busiRecordId = busiRecordId;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getCouponDetailId() {
		return this.couponDetailId;
	}

	public void setCouponDetailId(int couponDetailId) {
		this.couponDetailId = couponDetailId;
	}

}