package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the busi_product database table.
 * 
 */
public class BusiProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Date createdAt;

	private int createdBy;

	private Date endDate;

	private String prdtMemo;

	private String prdtName;

	private int prdtNum;

	private float prdtPrice;

	private float prdtPriceBuy;

	private int prdtType;

	private Date startDate;

	private Date updatedAt;

	private int updatedBy;

	public BusiProduct() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPrdtMemo() {
		return this.prdtMemo;
	}

	public void setPrdtMemo(String prdtMemo) {
		this.prdtMemo = prdtMemo;
	}

	public String getPrdtName() {
		return this.prdtName;
	}

	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}

	public int getPrdtNum() {
		return this.prdtNum;
	}

	public void setPrdtNum(int prdtNum) {
		this.prdtNum = prdtNum;
	}

	public float getPrdtPrice() {
		return this.prdtPrice;
	}

	public void setPrdtPrice(float prdtPrice) {
		this.prdtPrice = prdtPrice;
	}

	public float getPrdtPriceBuy() {
		return this.prdtPriceBuy;
	}

	public void setPrdtPriceBuy(float prdtPriceBuy) {
		this.prdtPriceBuy = prdtPriceBuy;
	}

	public int getPrdtType() {
		return this.prdtType;
	}

	public void setPrdtType(int prdtType) {
		this.prdtType = prdtType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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