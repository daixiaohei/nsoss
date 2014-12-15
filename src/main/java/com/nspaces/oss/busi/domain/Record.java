package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the record database table.
 * 
 */
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String busiLogicNo;

	private String busiNo;

	private String busiProductNo;

	private BigInteger busiRecordId;

	private Date createdAt;

	private int dataNum;

	private int deviceListId;

	private Date endDate;

	private String errorCode;

	private String errorMsg;

	private String receiveMsg;

	private String sendMsg;

	private Date startDate;

	private String status;

	private String transCode;

	public Record() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiLogicNo() {
		return this.busiLogicNo;
	}

	public void setBusiLogicNo(String busiLogicNo) {
		this.busiLogicNo = busiLogicNo;
	}

	public String getBusiNo() {
		return this.busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}

	public String getBusiProductNo() {
		return this.busiProductNo;
	}

	public void setBusiProductNo(String busiProductNo) {
		this.busiProductNo = busiProductNo;
	}

	public BigInteger getBusiRecordId() {
		return this.busiRecordId;
	}

	public void setBusiRecordId(BigInteger busiRecordId) {
		this.busiRecordId = busiRecordId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getDataNum() {
		return this.dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
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

	public String getReceiveMsg() {
		return this.receiveMsg;
	}

	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public String getSendMsg() {
		return this.sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

}