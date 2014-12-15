package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.UserDataDao;
import com.nspaces.oss.busi.domain.UserData;
import com.nspaces.oss.busi.dto.UserDataDTO;
import com.nspaces.oss.busi.dto.UserDataQueryDTO;

@Service
public class UserDataServiceImpl implements UserDataService {

	
	@Autowired
	UserDataDao userDataDao;
	
	@Override
	public UserData editUserData(UserData userData) throws Exception {
		return userDataDao.editUserData(userData);
	}

	@Override
	public boolean deleteUserData(Integer id) throws Exception {
		return userDataDao.deleteUserData(id);
	}

	@Override
	public List<UserDataDTO> findUserName() throws Exception {
		return userDataDao.findUserName();
	}

	@Override
	public List<UserDataDTO> findCityName() throws Exception {
		return userDataDao.findCityName();
	}

	@Override
	public Pagination<UserDataDTO> queryUserDataByPage(UserDataQueryDTO queryDTO) {
		return userDataDao.queryUserDataByPage(queryDTO);
	}

}
