package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.UserRole;
import com.nspaces.oss.busi.dto.UserRoleDTO;
import com.nspaces.oss.busi.dto.UserRoleQueryDTO;


public interface UserRoleService {

	/**
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public UserRole editUserRole(UserRole userRole) throws Exception;
	

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserRole(Integer id) throws Exception ;
	
	public List<UserRoleDTO> findUserName() throws Exception;
	
	public List<UserRoleDTO> findUserRoleName() throws Exception;
	
	public Pagination<UserRoleDTO> queryUserRoleByPage(UserRoleQueryDTO queryDTO);
}
