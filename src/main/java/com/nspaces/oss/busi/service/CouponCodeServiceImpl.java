package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.busi.dao.CouponDao;
import com.nspaces.oss.busi.dao.InitCouponCodeDao;
import com.nspaces.oss.busi.dto.CouponCodeDTO;
import com.nspaces.oss.busi.dto.CouponCodeQueryDTO;

@Service
public class CouponCodeServiceImpl implements CouponCodeService {

	@Autowired
	CouponDao couponDao;
	@Autowired
	InitCouponCodeDao initCouponCodeDao;
	
	@Override
	public List<CouponCodeDTO> findCodeNo(String codeNo) throws Exception{
		return couponDao.findCodeNo(codeNo);
	}
	
	@Override
	public boolean updateCodeNoStatus(Integer id) throws Exception{
		return couponDao.updateCodeNoStatus(id);
	}

	@Override
	public void initCoponCode()  {
		
		try {
			initCouponCodeDao.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
