package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.dto.BusinessDTO;
import com.nspaces.oss.busi.dto.BusinessQueryDTO;


public interface BusinessService {

	/**
	 * 插入或者更新 business
	 * @param business
	 * @return
	 * @throws Exception
	 */
	public Business editBusiness(Business business) throws Exception;
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiness(Integer id) throws Exception ;
	
	public List<BusinessDTO> queryAllBusinessId() throws Exception;
	
	public Pagination<BusinessDTO> queryBusinessByPage(BusinessQueryDTO queryDTO);
	
}
