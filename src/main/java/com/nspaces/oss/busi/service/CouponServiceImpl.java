package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.CouponDao;
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

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponDao couponDao;
	
	@Override
	public Coupon insertAndUpdate(Coupon coupon) throws Exception {
		return couponDao.insertAndUpdate(coupon);
	}

	@Override
	public Pagination<CouponDTO> queryCouponByPage(CouponQueryDTO queryDTO) {
		return couponDao.queryCouponByPage(queryDTO);
	}
	
	@Override
	public Pagination<CouponCodeDTO> queryCouponCodeByPage(CouponQueryDTO queryDTO) {
		return couponDao.queryCouponCodeByPage(queryDTO);
	}
	
	@Override
	public List<Cooperation> loadCoop(){
		return couponDao.loadCoop();
	}
	
	@Override
	public List<Business> loadBusi(){
		return couponDao.loadBusi();
	}
	
	@Override
	public List<DeviceList> loadDevi(){
		return couponDao.loadDevi();
	}

	@Override
    public boolean deleteCouponByLogic(Coupon coupon) {
	    return couponDao.deleteCouponByLogic(coupon);
    }

	@Override
    public Pagination<CouponBusinessDTO> queryCouponBusinessByPage(
            CouponQueryDTO queryDTO) {
	    return couponDao.queryCouponBusinessByPage(queryDTO);
    }

	@Override
    public Pagination<CouponDeviceDTO> queryCouponDeviceByPage(
            CouponQueryDTO queryDTO) {
	    return couponDao.queryCouponDeviceByPage(queryDTO);
    }

	@Override
    public CouponBusinessDTO insertAndUpdateCB(CouponBusiness couponBusiness)
            throws Exception {
	    return couponDao.insertAndUpdateCB(couponBusiness);
    }

	@Override
    public CouponDeviceDTO insertAndUpdateCD(CouponDevice couponDevice)
            throws Exception {
	    return couponDao.insertAndUpdateCD(couponDevice);
    }

	@Override
    public boolean deleteCouponDevi(CouponDevice couponDevice) {
	    return couponDao.deleteCouponDevi(couponDevice);
    }

	@Override
    public boolean deleteCouponBusi(CouponBusiness couponBusiness) {
	    return couponDao.deleteCouponBusi(couponBusiness);
    }

}
