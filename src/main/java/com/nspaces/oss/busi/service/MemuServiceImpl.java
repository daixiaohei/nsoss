package com.nspaces.oss.busi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.MenuDao;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.dto.MenuDTO;
import com.nspaces.oss.busi.dto.MenuQueryDTO;

@Service
public class MemuServiceImpl implements MemuService {

	@Autowired
	MenuDao menuDao;
	
	@Override
	public Memu insertAndUpdate(Memu memu) throws Exception {
		return menuDao.insertAndUpdate(memu);
	}

	@Override
	public boolean deleteMemu(Integer id) throws Exception{
		return menuDao.deleteMemu(id);
	}
	
	public Pagination<MenuDTO> queryMenuByPage(MenuQueryDTO queryDTO){
		return menuDao.queryMenuByPage(queryDTO);
	}
}
