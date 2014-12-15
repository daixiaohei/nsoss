package com.nspaces.oss.busi.dto;

import java.io.Serializable;

public class BusiDeviceSettingDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;


	private Integer busiId;//选业务（下拉框）

	private String cryptKey;//加密密钥（输入框）

	private String cryptKeyMain;//加密主密钥(输入框)


	private Integer deviceListId;//网点ID，（下拉或者弹出页面选择一个网点Id）

	private String deviceNo;//业务终端号(输入框)

	private String merchNo;//业务商户号(输入框)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusiId() {
		return busiId;
	}

	public void setBusiId(Integer busiId) {
		this.busiId = busiId;
	}

	public String getCryptKey() {
		return cryptKey;
	}

	public void setCryptKey(String cryptKey) {
		this.cryptKey = cryptKey;
	}

	public String getCryptKeyMain() {
		return cryptKeyMain;
	}

	public void setCryptKeyMain(String cryptKeyMain) {
		this.cryptKeyMain = cryptKeyMain;
	}

	public Integer getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	
	
}
