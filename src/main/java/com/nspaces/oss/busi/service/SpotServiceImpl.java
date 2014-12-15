package com.nspaces.oss.busi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.SpotDao;
import com.nspaces.oss.busi.domain.Spot;
import com.nspaces.oss.busi.dto.SpotDTO;
import com.nspaces.oss.busi.dto.SpotQueryDTO;
@Service
public class SpotServiceImpl implements SpotService{

	@Autowired
	private SpotDao spotDao;
	@Override
	public Spot insertAndUpdate(Spot spot) throws Exception {
		return spotDao.insertAndUpdate(spot);
	}

	@Override
	public Spot findSpotByName(String name) throws Exception {
		return spotDao.findSpotByName(name);
	}

	@Override
	public Pagination<SpotDTO> querySpotByPage(SpotQueryDTO queryDTO) {
		return spotDao.querySpotByPage(queryDTO);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		return spotDao.deleteById(id);
	}

}
