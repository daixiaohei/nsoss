package com.nspaces.oss.base.dto;

public class ErrorCode {

	private int code;
	
	private String errorMsg;
	
	public ErrorCode(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}
		
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 参数不正确
	 */
	public static final ErrorCode PARAM_EXCEPTION = new ErrorCode(100, "Parameter error");
	
	/**
	 * 查无数据
	 */
	public static final ErrorCode NO_DATA_FOUND = new ErrorCode(101, "No data found");
	
	/**
	 * 数据字段验证错误，插入或更新失败
	 */
	public static final ErrorCode DATA_FIELD_EXCEPTION = new ErrorCode(3001, "Field validation fail");
	
	/**
	 * 数据已存在验证错误，插入或更新失败
	 */
	public static final ErrorCode DATA_EXISTS = new ErrorCode(3002, "Data exist exception");
	
	/**
	 * 删除失败：数据已被引用
	 */
	public static final ErrorCode DATA_CONSTRAINT = new ErrorCode(3003, "Delete fail: Data Constraint");
	
	/**
	 * 数据操作失败：请求未被授权--非法操作不是自己的数据
	 */
	public static final ErrorCode DATA_UNAUTHORIZED = new ErrorCode(3004, "Request not authorized");
}
