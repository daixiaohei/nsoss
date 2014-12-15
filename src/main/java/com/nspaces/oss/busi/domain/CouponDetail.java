package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the coupon_detail database table.
 * 
 */
public class CouponDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private byte codeType;

	private BigDecimal coupValue;

	private int couponId;

	private Date createdAt;

	private int createdBy;

	private BigDecimal endRule;

	private BigDecimal startRule;

	private Date updatedAt;

	private int updatedBy;

	public CouponDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getCodeType() {
		return this.codeType;
	}

	public void setCodeType(byte codeType) {
		this.codeType = codeType;
	}

	public BigDecimal getCoupValue() {
		return this.coupValue;
	}

	public void setCoupValue(BigDecimal coupValue) {
		this.coupValue = coupValue;
	}

	public int getCouponId() {
		return this.couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
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

	public BigDecimal getEndRule() {
		return this.endRule;
	}

	public void setEndRule(BigDecimal endRule) {
		this.endRule = endRule;
	}

	public BigDecimal getStartRule() {
		return this.startRule;
	}

	public void setStartRule(BigDecimal startRule) {
		this.startRule = startRule;
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

}