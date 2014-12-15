package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the busi_pay_record database table.
 * 
 */
public class BusiPayRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String bankAcctName;

	private String bankAcctNo;

	private String bankAcctSite;

	private int busiId;

	private java.math.BigInteger busiRecordId;

	private byte cardType;

	private Date createdAt;

	private int deviceListId;

	private Date endDate;

	private String errorCode;

	private String errorMsg;

	private String merchCode;

	private byte payMethod;

	private int payMoney;

	private String posDeviceNo;

	private String posPayDate;

	private String posRecordNo;

	private String posVoucherNo;

	private Date startDate;

	private byte status;

	public BusiPayRecord() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public java.math.BigInteger getBusiRecordId() {
		return this.busiRecordId;
	}

	public void setBusiRecordId(java.math.BigInteger busiRecordId) {
		this.busiRecordId = busiRecordId;
	}

	public byte getCardType() {
		return this.cardType;
	}

	public void setCardType(byte cardType) {
		this.cardType = cardType;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getDeviceListId() {
		return this.deviceListId;
	}

	public void setDeviceListId(int deviceListId) {
		this.deviceListId = deviceListId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMerchCode() {
		return this.merchCode;
	}

	public void setMerchCode(String merchCode) {
		this.merchCode = merchCode;
	}

	public byte getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(byte payMethod) {
		this.payMethod = payMethod;
	}

	public int getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getPosDeviceNo() {
		return this.posDeviceNo;
	}

	public void setPosDeviceNo(String posDeviceNo) {
		this.posDeviceNo = posDeviceNo;
	}

	public String getPosPayDate() {
		return this.posPayDate;
	}

	public void setPosPayDate(String posPayDate) {
		this.posPayDate = posPayDate;
	}

	public String getPosRecordNo() {
		return this.posRecordNo;
	}

	public void setPosRecordNo(String posRecordNo) {
		this.posRecordNo = posRecordNo;
	}

	public String getPosVoucherNo() {
		return this.posVoucherNo;
	}

	public void setPosVoucherNo(String posVoucherNo) {
		this.posVoucherNo = posVoucherNo;
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

}