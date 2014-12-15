package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the busi_record database table.
 * 
 */
public class BusiRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private int busiId;

	private String busiLogicNo;

	private BigInteger busiPayRecordId;

	private String busiProductNo;

	private Date createdAt;

	private int deviceListId;

	private String errorCode;

	private byte isCheck;

	private byte isCustomer;

	private byte isPaidoff;

	private byte isRevoked;

	private byte isTraded;

	private String memo;

	private int payMoney;

	private byte status;

	public BusiRecord() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public String getBusiLogicNo() {
		return this.busiLogicNo;
	}

	public void setBusiLogicNo(String busiLogicNo) {
		this.busiLogicNo = busiLogicNo;
	}

	public BigInteger getBusiPayRecordId() {
		return this.busiPayRecordId;
	}

	public void setBusiPayRecordId(BigInteger busiPayRecordId) {
		this.busiPayRecordId = busiPayRecordId;
	}

	public String getBusiProductNo() {
		return this.busiProductNo;
	}

	public void setBusiProductNo(String busiProductNo) {
		this.busiProductNo = busiProductNo;
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

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public byte getIsCheck() {
		return this.isCheck;
	}

	public void setIsCheck(byte isCheck) {
		this.isCheck = isCheck;
	}

	public byte getIsCustomer() {
		return this.isCustomer;
	}

	public void setIsCustomer(byte isCustomer) {
		this.isCustomer = isCustomer;
	}

	public byte getIsPaidoff() {
		return this.isPaidoff;
	}

	public void setIsPaidoff(byte isPaidoff) {
		this.isPaidoff = isPaidoff;
	}

	public byte getIsRevoked() {
		return this.isRevoked;
	}

	public void setIsRevoked(byte isRevoked) {
		this.isRevoked = isRevoked;
	}

	public byte getIsTraded() {
		return this.isTraded;
	}

	public void setIsTraded(byte isTraded) {
		this.isTraded = isTraded;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}