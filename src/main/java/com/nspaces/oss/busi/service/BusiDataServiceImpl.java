package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.busi.dao.DataAnalysisDao;
import com.nspaces.oss.busi.dto.DataDetailDTO;
import com.nspaces.oss.busi.dto.DataGatherDTO;
import com.nspaces.oss.busi.dto.DataQueryDTO;

@Service
public class BusiDataServiceImpl implements BusiDataService {

	@Autowired
	DataAnalysisDao dataAnalysisDao;
	
	@Override
	public List<DataGatherDTO> findBusiGatherDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		return dataAnalysisDao.findBusiGatherDTO(dataQueryDTO);
	}

	@Override
	public List<DataGatherDTO> findBusiName() throws Exception{
		return dataAnalysisDao.findBusiName();
	}
	@Override
	public List<DataGatherDTO> findSpotBusiGatherDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		return dataAnalysisDao.findSpotBusiGatherDTO(dataQueryDTO);
	}
	@Override
	public List<DataGatherDTO> findSpotName() throws Exception{
		return dataAnalysisDao.findSpotName();
	}
	
	@Override
	public List<DataGatherDTO> findCityNo() throws Exception{
		return dataAnalysisDao.findCityNo();
	}

	@Override
	public List<DataDetailDTO> findSpotBusiDetailDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		return dataAnalysisDao.findSpotBusiDetailDTO(dataQueryDTO);
	}
	
	@Override
	public List<DataDetailDTO> findBusiStatus() throws Exception {
		return dataAnalysisDao.findBusiStatus();
	}

}
