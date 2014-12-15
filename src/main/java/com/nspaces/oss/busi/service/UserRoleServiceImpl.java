package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.UserDao;
import com.nspaces.oss.busi.dao.UserRoleDao;
import com.nspaces.oss.busi.domain.UserRole;
import com.nspaces.oss.busi.dto.UserRoleDTO;
import com.nspaces.oss.busi.dto.UserRoleQueryDTO;
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public UserRole editUserRole(UserRole userRole) throws Exception {
		return userRoleDao.editUserRole(userRole);
	}

	@Override
	public boolean deleteUserRole(Integer id) throws Exception {
		return userRoleDao.deleteUserRole(id);
	}
	@Override
	public Pagination<UserRoleDTO> queryUserRoleByPage(UserRoleQueryDTO queryDTO){
		return userRoleDao.queryUserRoleByPage(queryDTO);
	}
	
	@Override
	public List<UserRoleDTO> findUserName() throws Exception{
		return userRoleDao.findUserName();
	}
	
	@Override
	public List<UserRoleDTO> findUserRoleName() throws Exception{
		return userRoleDao.findUserRoleName();
	}

}
