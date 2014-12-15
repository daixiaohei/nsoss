package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the cooperation database table.
 * 
 */
public class Cooperation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private int accountSettleType;

	private int accountStateType;

	private String address;

	private String bankAcctName;

	private String bankAcctNo;

	private String bankAcctSite;

	private String contact;

	private String coopNo;

	private Date createdAt;

	private int createdBy;

	private Date endDate;

	private byte hasChild;

	private String memo;

	private byte merchType;

	private String name;

	private String nameFull;

	private Integer parentId;

	private String postcode;

	private Date startDate;

	private byte status;

	private String telephone;

	private Date updatedAt;

	private int updatedBy;

	public Cooperation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAccountSettleType() {
		return this.accountSettleType;
	}

	public void setAccountSettleType(int accountSettleType) {
		this.accountSettleType = accountSettleType;
	}

	public int getAccountStateType() {
		return this.accountStateType;
	}

	public void setAccountStateType(int accountStateType) {
		this.accountStateType = accountStateType;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCoopNo() {
		return this.coopNo;
	}

	public void setCoopNo(String coopNo) {
		this.coopNo = coopNo;
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

	public byte getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(byte hasChild) {
		this.hasChild = hasChild;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public byte getMerchType() {
		return this.merchType;
	}

	public void setMerchType(byte merchType) {
		this.merchType = merchType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFull() {
		return this.nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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