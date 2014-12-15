package com.nspaces.oss.busi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DataDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String busiId;// 业务ID
	private String deviceListId;
	private String statusId;
	private String deviceName; // 网点名称
	private String busiName;// 业务名称
	private BigDecimal payMoney;// 支付金额
	private String payStatus;// 支付状态
	private String errorCode;// 支付错误代码
	private String busiStatus; // 业务流水状态
	private String busiLogicNo;// 逻辑代码
	private String payTime;// 支付时间
	private String bankAcctNo;// 支付银行账号
	private String posRecordNo;// 支付记录号
	private String posDeviceNo;// 支付终端号
	private String createTime;// 支付终端号

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(String deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getBusiStatus() {
		return busiStatus;
	}

	public void setBusiStatus(String busiStatus) {
		this.busiStatus = busiStatus;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public String getPosRecordNo() {
		return posRecordNo;
	}

	public void setPosRecordNo(String posRecordNo) {
		this.posRecordNo = posRecordNo;
	}

	public String getPosDeviceNo() {
		return posDeviceNo;
	}

	public void setPosDeviceNo(String posDeviceNo) {
		this.posDeviceNo = posDeviceNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBusiLogicNo() {
		return busiLogicNo;
	}

	public void setBusiLogicNo(String busiLogicNo) {
		this.busiLogicNo = busiLogicNo;
	}

}
