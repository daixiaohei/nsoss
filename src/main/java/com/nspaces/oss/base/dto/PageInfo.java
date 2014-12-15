package com.nspaces.oss.base.dto;

import java.io.Serializable;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = -4983583936410058287L;

	private int page = 1;

	private int total;

	private int pageSize;

	private int maxPage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

}
