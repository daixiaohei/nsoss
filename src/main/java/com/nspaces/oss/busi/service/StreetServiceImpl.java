package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.StreetDao;
import com.nspaces.oss.busi.domain.Street;
import com.nspaces.oss.busi.dto.StreetDTO;
import com.nspaces.oss.busi.dto.StreetQueryDTO;
@Service
public class StreetServiceImpl implements StreetService{

	@Autowired
	private StreetDao streetDao;
	@Override
	public Street insertAndUpdate(Street street) throws Exception {
		return streetDao.insertAndUpdate(street);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		return streetDao.deleteById(id);
	}

	@Override
	public Street findStreetByName(String name) throws Exception {
		return streetDao.findStreetByName(name);
	}

	@Override
	public Pagination<StreetDTO> queryStreetByPage(StreetQueryDTO queryDTO) {
		return streetDao.queryCityByPage(queryDTO);
	}

	@Override
	public List<Street> findStreetByCityNo(String cityNo) throws Exception {
		return streetDao.findStreetByCityNo(cityNo);
	}

}
