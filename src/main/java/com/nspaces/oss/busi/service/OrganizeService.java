package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Organize;
import com.nspaces.oss.busi.domain.Role;
import com.nspaces.oss.busi.dto.OrganizeDTO;
import com.nspaces.oss.busi.dto.OrganizeQueryDTO;
import com.nspaces.oss.busi.dto.RoleDTO;
import com.nspaces.oss.busi.dto.RoleQueryDTO;


public interface OrganizeService {

	/**
	 * 插入或者更新organize
	 * @param organize
	 * @return
	 * @throws Exception
	 */
	public Organize editOrganize(Organize organize) throws Exception;
	

	/**
	 * 删除部门
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOrganize(Integer id) throws Exception ;
	
	/**
	 * 查询部门
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Pagination<OrganizeDTO> queryOrganizeByPage(OrganizeQueryDTO queryDTO);
	
	
}
