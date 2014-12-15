package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.UserDao;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.User;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User insertAndUpdate(User user) throws Exception {
		return userDao.insertAndUpdate(user);
	}

	@Override
	public User findUserByName(String name) throws Exception {
		return userDao.findUserByName(name);
	}

	@Override
	public Pagination<UserDTO> queryUserByPage(UserQueryDTO queryDTO) {
		return userDao.queryUserByPage(queryDTO);
	}
	
	public boolean deleteUserByLogic(User user){
		return userDao.deleteUserByLogic(user);
	}
	
	public List<Memu> loadMemu(Integer userId){
		return userDao.loadMemu(userId);
	}

}
