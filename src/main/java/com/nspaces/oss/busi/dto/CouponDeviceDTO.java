package com.nspaces.oss.busi.dto;

import com.nspaces.oss.busi.domain.CouponDevice;


public class CouponDeviceDTO extends CouponDevice{

    private static final long serialVersionUID = 3655056442421001352L;

    private String couponName;

    private String deviName;
    
    private boolean exist;
    
	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public String getDeviName() {
		return deviName;
	}

	public void setDeviName(String deviName) {
		this.deviName = deviName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

}
