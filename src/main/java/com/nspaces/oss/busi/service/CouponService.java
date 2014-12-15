package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.domain.Cooperation;
import com.nspaces.oss.busi.domain.Coupon;
import com.nspaces.oss.busi.domain.CouponBusiness;
import com.nspaces.oss.busi.domain.CouponDevice;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.CouponBusinessDTO;
import com.nspaces.oss.busi.dto.CouponCodeDTO;
import com.nspaces.oss.busi.dto.CouponDTO;
import com.nspaces.oss.busi.dto.CouponDeviceDTO;
import com.nspaces.oss.busi.dto.CouponQueryDTO;

public interface CouponService {

	public Coupon insertAndUpdate(Coupon coupon) throws Exception;
	
	public CouponBusinessDTO insertAndUpdateCB(CouponBusiness couponBusiness) throws Exception;
	
	public CouponDeviceDTO insertAndUpdateCD(CouponDevice couponDevice) throws Exception;
	
	public Pagination<CouponDTO> queryCouponByPage(CouponQueryDTO queryDTO);
	
	public Pagination<CouponCodeDTO> queryCouponCodeByPage(CouponQueryDTO queryDTO);
	
	public Pagination<CouponBusinessDTO> queryCouponBusinessByPage(CouponQueryDTO queryDTO);
	
	public Pagination<CouponDeviceDTO> queryCouponDeviceByPage(CouponQueryDTO queryDTO);
	
	public List<Cooperation> loadCoop();
	
	public List<Business> loadBusi();
	
	public List<DeviceList> loadDevi();
	
	public boolean deleteCouponByLogic(Coupon coupon);
	
	public boolean deleteCouponDevi(CouponDevice couponDevice);
	
	public boolean deleteCouponBusi(CouponBusiness couponBusiness);
}
