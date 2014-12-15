package com.nspaces.oss.busi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 数据汇总信息
 * 
 * @author Administrator
 * 
 */
public class DataGatherDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deviceName;
	private String busiName;
	private String busiId;
	private String deviceListId;
	private String busiStatus;
	private String cityNo;

	private Integer successCount;// 成功笔数
	private BigDecimal successMoney;// 成功的金额

	private Integer cancelCount;// 需人工退款笔数
	private BigDecimal cancelMoney;// 需人工退款金额

	private Integer autoCount;// 自动退款笔数
	private BigDecimal autoMoney;// 自动退款金额

	private Integer failCount;// 支付失败笔数

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getBusiStatus() {
		return busiStatus;
	}

	public void setBusiStatus(String busiStatus) {
		this.busiStatus = busiStatus;
	}

	public String getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(String deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	private BigDecimal failMoney;// 支付失败不许要退款金额

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

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
	}

	public Integer getCancelCount() {
		return cancelCount;
	}

	public void setCancelCount(Integer cancelCount) {
		this.cancelCount = cancelCount;
	}

	public BigDecimal getCancelMoney() {
		return cancelMoney;
	}

	public void setCancelMoney(BigDecimal cancelMoney) {
		this.cancelMoney = cancelMoney;
	}

	public Integer getAutoCount() {
		return autoCount;
	}

	public void setAutoCount(Integer autoCount) {
		this.autoCount = autoCount;
	}

	public BigDecimal getAutoMoney() {
		return autoMoney;
	}

	public void setAutoMoney(BigDecimal autoMoney) {
		this.autoMoney = autoMoney;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public BigDecimal getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(BigDecimal failMoney) {
		this.failMoney = failMoney;
	}

}
