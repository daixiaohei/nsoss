package com.nspaces.oss.busi.dto;

import com.nspaces.oss.busi.domain.CouponBusiness;


public class CouponBusinessDTO extends CouponBusiness{

    private static final long serialVersionUID = 3655056442421001352L;

    private String couponName;

    private String busiName;
    
    private boolean exist; 
    

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

}
