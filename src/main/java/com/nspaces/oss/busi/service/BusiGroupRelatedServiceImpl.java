package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.BusiGroupRelatedDao;
import com.nspaces.oss.busi.domain.BusiGroupRelated;
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedQueryDTO;

@Service
public class BusiGroupRelatedServiceImpl implements BusiGroupRelatedService {

	@Autowired
	BusiGroupRelatedDao busiGroupRelatedDao;
	
	@Override
	public BusiGroupRelated editBusiGroupRelated(
			BusiGroupRelated busiGroupRelated) throws Exception {
		return busiGroupRelatedDao.editBusiGroupRelated(busiGroupRelated);
	}

	@Override
	public boolean deleteBusiGroupRelated(Integer id) throws Exception {
		return busiGroupRelatedDao.deleteBusiGroupRelated(id);
	}

	@Override
	public Pagination<BusiGroupRelatedDTO> queryBusiGroupRelatedByPage(
			BusiGroupRelatedQueryDTO queryDTO) {
		return busiGroupRelatedDao.queryBusiGroupRelatedByPage(queryDTO);
	}

	@Override
	public List<BusiGroupRelatedDTO> findBusiGroupName() throws Exception {
		return busiGroupRelatedDao.findBuaiGroupName();
	}

	@Override
	public List<BusiGroupRelatedDTO> findBusiName() throws Exception {
		return busiGroupRelatedDao.findBusiName();
	}

}
