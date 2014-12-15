package com.nspaces.oss.busi.dto;


import com.nspaces.oss.busi.domain.News;

public class NewsDTO extends News {

	private String publishStatusId;

	public String getPublishStatusId() {
		return publishStatusId;
	}

	public void setPublishStatusId(String publishStatusId) {
		this.publishStatusId = publishStatusId;
	}

}
