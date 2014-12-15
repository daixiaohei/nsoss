package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.dao.BusiGroupDeviceDao;
import com.nspaces.oss.busi.domain.BusiGroupDevice;
import com.nspaces.oss.busi.dto.BusiGroupDeviceDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceQueryDTO;

@Service
public class BusiGroupDeviceServiceImpl implements BusiGroupDeviceService {

	@Autowired
	BusiGroupDeviceDao busiGroupDeviceDao;
	
	@Override
	public BusiGroupDevice editBusiGroupDevice(BusiGroupDevice busiGroupDevice)
			throws Exception {
		return busiGroupDeviceDao.editBusiGroupDevice(busiGroupDevice);
	}

	@Override
	public boolean deleteBusiGroupDevice(Integer id) throws Exception {
		return busiGroupDeviceDao.deleteBusiGroupDevice(id);
	}

	@Override
	public Pagination<BusiGroupDeviceDTO> queryBusiGroupDeviceByPage(
			BusiGroupDeviceQueryDTO queryDTO) {
		return busiGroupDeviceDao.queryBusiGroupDeviceByPage(queryDTO);
	}

	@Override
	public List<BusiGroupDeviceDTO> findBusiGroupName() throws Exception {
		return busiGroupDeviceDao.findBuaiGroupName();
	}

	@Override
	public List<BusiGroupDeviceDTO> findDeviceListName() throws Exception {
		return busiGroupDeviceDao.findDeviceListName();
	}

}
