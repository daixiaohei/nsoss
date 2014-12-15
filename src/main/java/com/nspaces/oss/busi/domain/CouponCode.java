package com.nspaces.oss.busi.domain;

import java.io.Serializable;

/**
 * The persistent class for the coupon_code database table.
 * 
 */
public class CouponCode implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String codeNo;

	private String codeType;

	private int couponId;

	private String status;

	private String printDate;
	
	private String useDate;
	
	private String couponMoney;
	
	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	public CouponCode() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}