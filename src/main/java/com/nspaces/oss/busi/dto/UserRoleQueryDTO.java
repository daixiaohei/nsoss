package com.nspaces.oss.busi.dto;

import com.nspaces.oss.base.dto.PaginationCondition;

/**
 * 
 * @author whw
 * 
 */
public class UserRoleQueryDTO {

	// 查询条件
	private String userName;

	// 分页参数
	private Integer start;
	private Integer limit;
	public Integer curPage;
	public PaginationCondition paginationCondition = new PaginationCondition();

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		if (this.curPage != null && this.curPage > 1) {
			paginationCondition.setPage(this.curPage);
		}
		if (this.limit != null && this.limit > 1) {
			paginationCondition.setPageSize(this.limit);
		}
		return paginationCondition;
	}

	public void setPaginationCondition(PaginationCondition paginationCondition) {
		this.paginationCondition = paginationCondition;
	}

}
