package com.nspaces.oss.busi.dto;

import com.nspaces.oss.base.dto.PaginationCondition;

/**
 * @author ZK
 */
public class CouponQueryDTO{

	//查询条件
	private String name;//优惠券名称
	
	private String couponId;//优惠券ID --详细功能
	private String codeNo;//优惠券代码
	private String status;//使用状态
	
	private String busiName;//业务名  --绑定业务功能
	private String deviName;//网点名  --绑定网点功能
	

	//分页参数
	private Integer start;
	private Integer limit;
	public Integer curPage;
	public PaginationCondition paginationCondition = new PaginationCondition();

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getDeviName() {
		return deviName;
	}

	public void setDeviName(String deviName) {
		this.deviName = deviName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
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
