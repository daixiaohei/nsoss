package com.nspaces.oss.base.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 5788448411192393614L;
	
	public static final boolean DEFAULT_RESULTS = true;
	
	private boolean result = DEFAULT_RESULTS;
	
	private int page = 1;
	
	private int total;
	
	private int pageSize;
	
	private int maxPage;
	
	private List<T> items;
	
	private Object retObject; //缓存除了对象之外，还有其它的对象
	

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
		if(pageSize == 0)
			return 1;
		maxPage = (total + pageSize - 1) / pageSize;
		if (maxPage <= 0) {
			maxPage = 1;
		}
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public List<T> getItems() {
		if(items == null) {
			items = new ArrayList<T>();
		}
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
	
	public PageInfo generatePageInfo() {
		PageInfo info = new PageInfo();
		info.setMaxPage(maxPage);
		info.setPage(page);
		info.setPageSize(pageSize);
		info.setTotal(total);
		return info;
	}
	
	public List<T> generateItem() {
		return this.items;
	}
	/**
	 * @author zhangbingyue
	 * @version 创建时间 2013-1-29 下午4:47:17
	 * @return
	 */
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", total=" + total + ", pageSize="
				+ pageSize + ", maxPage=" + maxPage + ", items=" + items + "]";
	}

	public Object getRetObject() {
		return retObject;
	}

	public void setRetObject(Object retObject) {
		this.retObject = retObject;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
