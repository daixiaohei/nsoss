package com.nspaces.oss.busi.dto;

import com.nspaces.oss.base.dto.PaginationCondition;

public class StreetQueryDTO {

	//查询条件
	private String address;
	private String cityNo;
	private String name;
	private String nameFull;
	
	
	//分页参数
	private Integer start;
	private Integer limit;
    public Integer curPage;
	public PaginationCondition paginationCondition = new PaginationCondition();
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameFull() {
		return nameFull;
	}
	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
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
