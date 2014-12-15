package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the merchant database table.
 * 
 */
public class Merchant implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String bankAcctName;

	private String bankAcctNo;

	private String bankAcctSite;

	private Date createdAt;

	private int createdBy;

	private String memo;

	private String merchCode;

	private String merchDeviceNo;

	private String merchPosCode;

	private String name;

	private byte status;

	private Date updatedAt;

	private int updatedBy;

	public Merchant() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAcctName() {
		return this.bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBankAcctNo() {
		return this.bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public String getBankAcctSite() {
		return this.bankAcctSite;
	}

	public void setBankAcctSite(String bankAcctSite) {
		this.bankAcctSite = bankAcctSite;
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

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMerchCode() {
		return this.merchCode;
	}

	public void setMerchCode(String merchCode) {
		this.merchCode = merchCode;
	}

	public String getMerchDeviceNo() {
		return this.merchDeviceNo;
	}

	public void setMerchDeviceNo(String merchDeviceNo) {
		this.merchDeviceNo = merchDeviceNo;
	}

	public String getMerchPosCode() {
		return this.merchPosCode;
	}

	public void setMerchPosCode(String merchPosCode) {
		this.merchPosCode = merchPosCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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