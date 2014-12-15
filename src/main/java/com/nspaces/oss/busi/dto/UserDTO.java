package com.nspaces.oss.busi.dto;

import com.nspaces.oss.busi.domain.User;


public class UserDTO extends User{

	private static final long serialVersionUID = 1L;
	
	private String deptName;
	
	private String verifycode;//验证码
	
	private String rememberMe;//记住我?

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	
}
