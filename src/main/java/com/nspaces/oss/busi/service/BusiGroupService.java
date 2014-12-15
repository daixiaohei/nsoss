package com.nspaces.oss.busi.service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.BusiGroup;
import com.nspaces.oss.busi.dto.BusiGroupDTO;
import com.nspaces.oss.busi.dto.BusiGroupQueryDTO;


public interface BusiGroupService {

	/**
	 * 插入或者更新 busi_group
	 * @param business
	 * @return
	 * @throws Exception
	 */
	public BusiGroup editBusiGroup(BusiGroup busiGroup) throws Exception;
	
	/**
	 * 删除业务分组
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiGroup(Integer id) throws Exception ;
	
	public Pagination<BusiGroupDTO> queryBusiGroupByPage(BusiGroupQueryDTO queryDTO);
	
}
