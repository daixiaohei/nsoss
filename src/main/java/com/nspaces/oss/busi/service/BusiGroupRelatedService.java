package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.BusiGroupRelated;
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedQueryDTO;


public interface BusiGroupRelatedService {

	/**
	 * @param roleMenu
	 * @return
	 * @throws Exception
	 */
	public BusiGroupRelated editBusiGroupRelated(BusiGroupRelated busiGroupRelated) throws Exception;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiGroupRelated(Integer id) throws Exception;
	
	/**
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public Pagination<BusiGroupRelatedDTO> queryBusiGroupRelatedByPage(BusiGroupRelatedQueryDTO queryDTO);
	
	public List<BusiGroupRelatedDTO> findBusiGroupName() throws Exception;
	
	public List<BusiGroupRelatedDTO> findBusiName() throws Exception;
}
