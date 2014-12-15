package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.OrganizeDao;
import com.nspaces.oss.busi.domain.Organize;
import com.nspaces.oss.busi.dto.OrganizeDTO;
import com.nspaces.oss.busi.dto.OrganizeQueryDTO;

@Service
public class OrganizeServiceImpl implements OrganizeService {

	@Autowired
	OrganizeDao organizeDao;
	
	@Override
	public Organize editOrganize(Organize organize) throws Exception {
		return organizeDao.editOrganize(organize);
	}

	@Override
	public boolean deleteOrganize(Integer id) throws Exception {
		return organizeDao.deleteOrganize(id);
	}
	@Override
	public Pagination<OrganizeDTO> queryOrganizeByPage(OrganizeQueryDTO queryDTO){
		return organizeDao.queryOrganizeByPage(queryDTO);
	}

}
