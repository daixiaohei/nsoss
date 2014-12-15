package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.CityDao;
import com.nspaces.oss.busi.domain.City;
import com.nspaces.oss.busi.dto.CityDTO;
import com.nspaces.oss.busi.dto.CityQueryDTO;
@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	@Override
	public City insertAndUpdate(City city) throws Exception {
		return cityDao.insertAndUpdate(city);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		return cityDao.deleteById(id);
	}

	@Override
	public City findCityByName(String name) throws Exception {
		return cityDao.findCityByName(name);
	}

	@Override
	public Pagination<CityDTO> queryCityByPage(CityQueryDTO queryDTO) {
		return cityDao.queryCityByPage(queryDTO);
	}

	@Override
	public List<City> findCityByLevel(String cityLevel) throws Exception {
		return cityDao.findCityByLevel(cityLevel);
	}

	@Override
	public List<City> findAllChildrenCity(String cityLevel, String parentNo) throws Exception {
		return cityDao.findAllChildrenCity(cityLevel, parentNo);
	}

}
