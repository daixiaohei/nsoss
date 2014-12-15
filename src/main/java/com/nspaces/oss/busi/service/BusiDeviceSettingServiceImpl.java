package com.nspaces.oss.busi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.BusiDeviceSettingDao;
import com.nspaces.oss.busi.domain.BusiDeviceSetting;
import com.nspaces.oss.busi.dto.BusiDeviceSettingDTO;
import com.nspaces.oss.busi.dto.BusiDeviceSettingQueryDTO;
@Service
public class BusiDeviceSettingServiceImpl implements BusiDeviceSettingService{
	@Autowired
	private BusiDeviceSettingDao busiDeviceSettingDao;
	@Override
	public BusiDeviceSetting insertAndUpdate(BusiDeviceSetting setting)
			throws Exception {
		return busiDeviceSettingDao.insertAndUpdate(setting);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		return busiDeviceSettingDao.deleteById(id);
	}

	@Override
	public BusiDeviceSetting findBusiDeviceSettingByMerchNo(String merchNo)
			throws Exception {
		return busiDeviceSettingDao.findBusiDeviceSettingByMerchNo(merchNo);
	}

	@Override
	public Pagination<BusiDeviceSettingDTO> queryBusiDeviceSettingByPage(
			BusiDeviceSettingQueryDTO queryDTO) {
		return busiDeviceSettingDao.queryBusiDeviceSettingByPage(queryDTO);
	}

}
