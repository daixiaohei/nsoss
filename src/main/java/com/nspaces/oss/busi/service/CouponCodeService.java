package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.busi.dto.CouponCodeDTO;
import com.nspaces.oss.busi.dto.CouponCodeQueryDTO;


public interface CouponCodeService {

	/**
	 * 查询优惠券代码信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<CouponCodeDTO> findCodeNo(String codeNo) throws Exception;
	public boolean updateCodeNoStatus(Integer id) throws Exception;
	public void initCoponCode();
	
	
}
