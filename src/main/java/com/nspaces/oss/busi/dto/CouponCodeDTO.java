package com.nspaces.oss.busi.dto;

import com.nspaces.oss.busi.domain.CouponCode;


public class CouponCodeDTO extends CouponCode{

    private static final long serialVersionUID = 3655056442421001352L;

    private String couponName;

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

}
