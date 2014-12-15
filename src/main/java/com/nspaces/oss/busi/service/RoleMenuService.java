package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.RoleMenu;
import com.nspaces.oss.busi.dto.OrganizeDTO;
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.RoleMenuQueryDTO;


public interface RoleMenuService {

	/**
	 * @param roleMenu
	 * @return
	 * @throws Exception
	 */
	public RoleMenu editRoleMenu(RoleMenu roleMenu) throws Exception;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRoleMenu(Integer id) throws Exception;
	
	/**
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public Pagination<RoleMenuDTO> queryRoleMenuByPage(RoleMenuQueryDTO queryDTO);
	
	public List<RoleMenuDTO> findRoleName() throws Exception;
	
	public List<RoleMenuDTO> findMenuName() throws Exception;
}
