package com.nspaces.oss.busi.dto;

import java.util.Date;

import com.nspaces.oss.base.dto.PaginationCondition;

public class PartQueryDTO {

	//查询信息
	private Integer id;
	private Date createdAt;
	private Integer createdBy;
	private Integer deviceId;
	private byte logicType;
	private String memo;
	private String name;
	private String partNo;
	private byte partType;//0增值设备，1感应设备，2视频设备
	private Date updatedAt;
	private Integer updatedBy;
	private String vender;
	
	//分页参数
	private Integer start;
	private Integer limit;
    public Integer curPage;
	public PaginationCondition paginationCondition = new PaginationCondition();

	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public byte getLogicType() {
		return logicType;
	}
	public void setLogicType(byte logicType) {
		this.logicType = logicType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public byte getPartType() {
		return partType;
	}
	public void setPartType(byte partType) {
		this.partType = partType;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
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
		if (this.curPage != null && this.curPage>1) {
			paginationCondition.setPage(this.curPage);
		}
		if (this.limit != null && this.limit>1) {
			paginationCondition.setPageSize(this.limit);
		}
		return paginationCondition;
	}
	public void setPaginationCondition(PaginationCondition paginationCondition) {
		this.paginationCondition = paginationCondition;
	}
	
	
}
