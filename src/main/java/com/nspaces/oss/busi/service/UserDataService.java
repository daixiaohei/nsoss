package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.UserData;
import com.nspaces.oss.busi.domain.UserRole;
import com.nspaces.oss.busi.dto.UserDataDTO;
import com.nspaces.oss.busi.dto.UserDataQueryDTO;
import com.nspaces.oss.busi.dto.UserRoleDTO;
import com.nspaces.oss.busi.dto.UserRoleQueryDTO;


public interface UserDataService {

	public UserData editUserData(UserData userData) throws Exception;
	
	public boolean deleteUserData(Integer id) throws Exception ;
	
	public List<UserDataDTO> findUserName() throws Exception;
	
	public List<UserDataDTO> findCityName() throws Exception;
	
	public Pagination<UserDataDTO> queryUserDataByPage(UserDataQueryDTO queryDTO);
}
