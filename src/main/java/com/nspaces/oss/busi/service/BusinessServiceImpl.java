package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.BusinessDao;
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.dto.BusinessDTO;
import com.nspaces.oss.busi.dto.BusinessQueryDTO;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	BusinessDao businessDao;

	@Override
	public Business editBusiness(Business business) throws Exception {
		return businessDao.editBusiness(business);
	}

	@Override
	public boolean deleteBusiness(Integer id) throws Exception {
		return businessDao.deleteBusiness(id);
	}

	@Override
	public Pagination<BusinessDTO> queryBusinessByPage(BusinessQueryDTO queryDTO) {
		return businessDao.queryBusinessByPage(queryDTO);
	}

	@Override
	public List<BusinessDTO> queryAllBusinessId() throws Exception {
		return businessDao.queryAllBusinessId();
	}

}
