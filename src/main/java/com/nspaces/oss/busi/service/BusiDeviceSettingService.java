package com.nspaces.oss.busi.service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.BusiDeviceSetting;
import com.nspaces.oss.busi.dto.BusiDeviceSettingDTO;
import com.nspaces.oss.busi.dto.BusiDeviceSettingQueryDTO;

public interface BusiDeviceSettingService {
	public BusiDeviceSetting insertAndUpdate(BusiDeviceSetting setting) throws Exception;
	
	public Boolean deleteById(Integer id)throws Exception;
	
	public BusiDeviceSetting findBusiDeviceSettingByMerchNo(String merchNo) throws Exception;
		
	public Pagination<BusiDeviceSettingDTO> queryBusiDeviceSettingByPage(BusiDeviceSettingQueryDTO queryDTO);
	
}
