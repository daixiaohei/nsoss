package com.nspaces.oss.busi.dto;

import com.nspaces.oss.busi.domain.Coupon;


public class CouponDTO extends Coupon{

    private static final long serialVersionUID = 3655056442421001352L;

    private String coopName;

	public String getCoopName() {
		return coopName;
	}

	public void setCoopName(String coopName) {
		this.coopName = coopName;
	}
}
