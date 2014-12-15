package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.RoleMenuDao;
import com.nspaces.oss.busi.domain.RoleMenu;
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.RoleMenuQueryDTO;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	
	@Autowired
	RoleMenuDao roleMenuDao;
	
	@Override
	public RoleMenu editRoleMenu(RoleMenu roleMenu) throws Exception {
		return roleMenuDao.editRoleMenu(roleMenu);
	}

	@Override
	public boolean deleteRoleMenu(Integer id) throws Exception {
		return roleMenuDao.deleteRoleMenu(id);
	}
	
	@Override
	public Pagination<RoleMenuDTO> queryRoleMenuByPage(RoleMenuQueryDTO queryDTO){
		return roleMenuDao.queryRoleMenuByPage(queryDTO);
	}
	
	@Override
	public List<RoleMenuDTO> findRoleName() throws Exception{
		return roleMenuDao.findRoleName();
	}
	
	@Override
	public List<RoleMenuDTO> findMenuName() throws Exception{
		return roleMenuDao.findMenuName();
	}
}
