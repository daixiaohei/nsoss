package com.nspaces.oss.base.dto;

import java.io.Serializable;

public class PaginationCondition implements Serializable {

	private static final long serialVersionUID = -7710240600295999589L;
	
	public static final int DEFAULT_PAGE = 1;
	
	public static final int DEFAULT_PAGESIZE = 10;

	private int page = DEFAULT_PAGE;
	
	private int pageSize = DEFAULT_PAGESIZE;
	
	public PaginationCondition() {
	}

	public PaginationCondition(Integer page, Integer pageSize) {
		if(page != null)
			this.page = page;
		if(pageSize != null)
			this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getOffset() {
		return (page - 1) * pageSize;
	}

	@Override
	public String toString() {
		return "PaginationCondition [page=" + page + ", pageSize=" + pageSize
				+ "]";
	}
}
