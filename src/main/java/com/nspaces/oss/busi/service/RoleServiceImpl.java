package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.RoleDao;
import com.nspaces.oss.busi.domain.Role;
import com.nspaces.oss.busi.dto.RoleDTO;
import com.nspaces.oss.busi.dto.RoleQueryDTO;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Override
	public Role editRole(Role role) throws Exception {
		return roleDao.editRole(role);
	}
	@Override
	public List<Role> findRoleByName(String name) throws Exception{
		return roleDao.findRoleByName(name);
	}
	@Override
	public boolean deleteRole(Integer id) throws Exception{
		return roleDao.deleteRole(id);
	}
	@Override
	public Pagination<RoleDTO> queryRoleByPage(RoleQueryDTO queryDTO){
		return roleDao.queryRoleByPage(queryDTO);
	}

}
