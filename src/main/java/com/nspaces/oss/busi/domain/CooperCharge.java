package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the cooper_charge database table.
 * 
 */
public class CooperCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private byte chargeType;

	private float chargeValue;

	private int cooperId;

	private Date createdAt;

	private int createdBy;

	private Date endDate;

	private String memo;

	private Date startDate;

	private byte status;

	private Date updatedAt;

	private int updatedBy;

	public CooperCharge() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(byte chargeType) {
		this.chargeType = chargeType;
	}

	public float getChargeValue() {
		return this.chargeValue;
	}

	public void setChargeValue(float chargeValue) {
		this.chargeValue = chargeValue;
	}

	public int getCooperId() {
		return this.cooperId;
	}

	public void setCooperId(int cooperId) {
		this.cooperId = cooperId;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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