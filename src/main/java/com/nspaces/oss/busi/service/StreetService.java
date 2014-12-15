package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Street;
import com.nspaces.oss.busi.dto.StreetDTO;
import com.nspaces.oss.busi.dto.StreetQueryDTO;

public interface StreetService {
	public Street insertAndUpdate(Street street) throws Exception;
	
	public Boolean deleteById(Integer id)throws Exception;
	
	public Street findStreetByName(String name) throws Exception;
		
	public Pagination<StreetDTO> queryStreetByPage(StreetQueryDTO queryDTO);
	
	public List<Street> findStreetByCityNo(String cityNo) throws Exception;
}
