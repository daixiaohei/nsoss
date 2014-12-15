package com.nspaces.oss.busi.service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Spot;
import com.nspaces.oss.busi.dto.SpotDTO;
import com.nspaces.oss.busi.dto.SpotQueryDTO;

public interface SpotService {

	public Spot insertAndUpdate(Spot spot) throws Exception;
	
	public Boolean deleteById(Integer id)throws Exception;
	
	public Spot findSpotByName(String name) throws Exception;
		
	public Pagination<SpotDTO> querySpotByPage(SpotQueryDTO queryDTO);
}
