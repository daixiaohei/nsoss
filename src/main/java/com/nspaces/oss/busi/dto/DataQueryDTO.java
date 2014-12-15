package com.nspaces.oss.busi.dto;

import java.util.Date;

import com.nspaces.oss.base.dto.PaginationCondition;

/**
 * 数据分析，公共查询条件
 * 
 * @author Administrator
 * 
 */
public class DataQueryDTO {

	private Integer busiId; // 业务类型 为空则查询所有的业务类型
	private String statusId;
	private String cityNo; // 区域代码 为空则查询所有的区域
	private Integer deviceListId; // 网点Id //为空则查询所有网点
	private Integer busiStatus; // 业务状态
	private Integer posStatus; // 支付状态

	private String startDate;// 开始类型
	private String endDate;// 结束类型

	// 分页参数，用于明细查询
	private Integer start;
	private Integer limit;
	public Integer curPage;
	public PaginationCondition paginationCondition = new PaginationCondition();

	private String busiName;
	private String deviceName;

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
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

	public Integer getBusiId() {
		return busiId;
	}

	public void setBusiId(Integer busiId) {
		this.busiId = busiId;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public Integer getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	public Integer getBusiStatus() {
		return busiStatus;
	}

	public void setBusiStatus(Integer busiStatus) {
		this.busiStatus = busiStatus;
	}

	public Integer getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(Integer posStatus) {
		this.posStatus = posStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public PaginationCondition getPaginationCondition() {
		return paginationCondition;
	}

	public void setPaginationCondition(PaginationCondition paginationCondition) {
		this.paginationCondition = paginationCondition;
	}

}
