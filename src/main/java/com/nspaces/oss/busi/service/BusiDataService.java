package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nspaces.oss.busi.dto.DataDetailDTO;
import com.nspaces.oss.busi.dto.DataGatherDTO;
import com.nspaces.oss.busi.dto.DataQueryDTO;


public interface BusiDataService {

	/**
	 * 查询业务汇总信息
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findBusiGatherDTO(DataQueryDTO dataQueryDTO) throws Exception;
	
	/**
	 * 查询业务类型
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findBusiName() throws Exception;
	
	/**
	 * 网点业务汇总表
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findSpotBusiGatherDTO(DataQueryDTO dataQueryDTO) throws Exception;
	
	/**
	 * 查询网点名
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findSpotName() throws Exception;
	
	/**
	 * 查询区域编号
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findCityNo() throws Exception;
	
	/**
	 * 查询网点交易明细表
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataDetailDTO> findSpotBusiDetailDTO(DataQueryDTO dataQueryDTO) throws Exception;
	
	/**
	 * 查询业务状态
	 * @return
	 * @throws Exception
	 */
	public List<DataDetailDTO> findBusiStatus() throws Exception ;
	
	
}
