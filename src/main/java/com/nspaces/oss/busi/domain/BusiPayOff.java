package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the busi_pay_off database table.
 * 
 */
public class BusiPayOff implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private byte autoOff;

	private Date createdAt;

	private int createdBy;

	private int customerId;

	private int deviceListId;

	private Date endDate;

	private int payMoney;

	private String payOffRecordNo;

	private String payOffVoucherNo;

	private BigInteger payRecordId;

	private Date payTime;

	private int payUserId;

	private String posDeviceNo;

	private Date startDate;

	private byte status;

	private Date updatedAt;

	private int updatedBy;

	public BusiPayOff() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getAutoOff() {
		return this.autoOff;
	}

	public void setAutoOff(byte autoOff) {
		this.autoOff = autoOff;
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

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayOffRecordNo() {
		return this.payOffRecordNo;
	}

	public void setPayOffRecordNo(String payOffRecordNo) {
		this.payOffRecordNo = payOffRecordNo;
	}

	public String getPayOffVoucherNo() {
		return this.payOffVoucherNo;
	}

	public void setPayOffVoucherNo(String payOffVoucherNo) {
		this.payOffVoucherNo = payOffVoucherNo;
	}

	public BigInteger getPayRecordId() {
		return this.payRecordId;
	}

	public void setPayRecordId(BigInteger payRecordId) {
		this.payRecordId = payRecordId;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public int getPayUserId() {
		return this.payUserId;
	}

	public void setPayUserId(int payUserId) {
		this.payUserId = payUserId;
	}

	public String getPosDeviceNo() {
		return this.posDeviceNo;
	}

	public void setPosDeviceNo(String posDeviceNo) {
		this.posDeviceNo = posDeviceNo;
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