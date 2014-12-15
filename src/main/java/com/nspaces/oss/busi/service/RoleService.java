package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Role;
import com.nspaces.oss.busi.dto.RoleDTO;
import com.nspaces.oss.busi.dto.RoleQueryDTO;


public interface RoleService {

	/**
	 * 插入或者更新Role
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Role editRole(Role role) throws Exception;
	
	/**
	 * 通过名字查找角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> findRoleByName(String name) throws Exception;
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRole(Integer id) throws Exception ;
	
	public Pagination<RoleDTO> queryRoleByPage(RoleQueryDTO queryDTO);
	
}
