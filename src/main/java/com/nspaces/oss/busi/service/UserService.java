package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.User;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;


public interface UserService {

	public User insertAndUpdate(User user) throws Exception;
	
	public User findUserByName(String name) throws Exception;
	
	public Pagination<UserDTO> queryUserByPage(UserQueryDTO queryDTO);
	
	public boolean deleteUserByLogic(User user);
	
	public List<Memu> loadMemu(Integer userId);
}
