package com.nspaces.oss.busi.dto;

import com.nspaces.oss.base.dto.PaginationCondition;

/**
 */
public class NewsQueryDTO {

	// 查询条件
	private String title;
	private String publishDate;
	private Integer publishStatus;
	private String publishStatusId;
	private String publishDate1;
	private String newsTypeId;
	private Integer newsType;

	public String getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(String newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public String getPublishDate1() {
		return publishDate1;
	}

	public void setPublishDate1(String publishDate1) {
		this.publishDate1 = publishDate1;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
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

	public String getPublishStatusId() {
		return publishStatusId;
	}

	public void setPublishStatusId(String publishStatusId) {
		this.publishStatusId = publishStatusId;
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
