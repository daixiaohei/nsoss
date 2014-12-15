package com.nspaces.oss.base.dto;

import java.io.Serializable;


public class JsonObject implements Serializable {

	private static final long serialVersionUID = -2744990363853889888L;

	public static final String TYPE_HTML = "html";
	public static final String TYPE_JSON = "json";

	private String status = Status.STATUS_SUCCESS;

	private String errorCode;
	
	private String message = "";

	private String type;

	private Object data;
	
	private PageInfo page = new PageInfo();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}
	
	public void setErrorMessage(String message){
		this.setMessage(message);
		this.setStatus(Status.STATUS_ERROR);
	}
	
	public void setSuccessMessage(String message) {
		this.setMessage(message);
		this.setStatus(Status.STATUS_SUCCESS);
	}

	@Override
	public String toString() {
		return "JsonObject [status=" + status + ", type=" + type + ", data="
				+ data + ", page=" + page + "]";
	}

	
}
