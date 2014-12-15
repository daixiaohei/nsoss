package com.nspaces.oss.busi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.BusiGroupDao;
import com.nspaces.oss.busi.domain.BusiGroup;
import com.nspaces.oss.busi.dto.BusiGroupDTO;
import com.nspaces.oss.busi.dto.BusiGroupQueryDTO;

@Service
public class BusiGroupServiceImpl implements BusiGroupService {

	@Autowired
	BusiGroupDao busiGroupDao;
	
	@Override
	public BusiGroup editBusiGroup(BusiGroup busiGroup) throws Exception {
		return busiGroupDao.editBusiGroup(busiGroup);
	}

	@Override
	public boolean deleteBusiGroup(Integer id) throws Exception {
		return busiGroupDao.deleteBusiGroup(id);
	}

	@Override
	public Pagination<BusiGroupDTO> queryBusiGroupByPage(
			BusiGroupQueryDTO queryDTO) {
		return busiGroupDao.queryBusiGroupByPage(queryDTO);
	}

}
