package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.City;
import com.nspaces.oss.busi.dto.CityDTO;
import com.nspaces.oss.busi.dto.CityQueryDTO;

public interface CityService {
	public City insertAndUpdate(City city) throws Exception;
	
	public Boolean deleteById(Integer id)throws Exception;
	
	public City findCityByName(String name) throws Exception;
		
	public Pagination<CityDTO> queryCityByPage(CityQueryDTO queryDTO);
	
	public List<City> findCityByLevel(String cityLevel)throws Exception;
	
	public List<City> findAllChildrenCity(String cityLevel,String parentNo) throws Exception;
}
