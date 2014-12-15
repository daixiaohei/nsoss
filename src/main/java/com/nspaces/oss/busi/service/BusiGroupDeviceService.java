package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.BusiGroupDevice;
import com.nspaces.oss.busi.dto.BusiGroupDeviceDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceQueryDTO;


public interface BusiGroupDeviceService {

	/**
	 * @param roleMenu
	 * @return
	 * @throws Exception
	 */
	public BusiGroupDevice editBusiGroupDevice(BusiGroupDevice busiGroupDevice) throws Exception;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiGroupDevice(Integer id) throws Exception;
	
	/**
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public Pagination<BusiGroupDeviceDTO> queryBusiGroupDeviceByPage(BusiGroupDeviceQueryDTO queryDTO);
	
	public List<BusiGroupDeviceDTO> findBusiGroupName() throws Exception;
	
	public List<BusiGroupDeviceDTO> findDeviceListName() throws Exception;
}
