package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.util.StringUtil;
import com.nspaces.oss.busi.dao.PartDao;
import com.nspaces.oss.busi.domain.Part;
import com.nspaces.oss.busi.domain.PartList;
import com.nspaces.oss.busi.dto.PartListDTO;
import com.nspaces.oss.busi.dto.PartListQueryDTO;
import com.nspaces.oss.busi.dto.PartQueryDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	PartDao partDao;
	
	@Override
	public Part insertAndUpdatePart(Part part) throws Exception {
		return partDao.insertAndUpdatePart(part);
	}

	@Override
	public Part findPartById(Integer id) throws Exception {
		return partDao.findPartById(id);
	}

	@Override
	public List<Part> findPartByPartType(Integer partType) throws Exception {
		return partDao.findPartByPartType(partType);
	}

	@Override
	public PartList insertAndUpdatePartList(PartList partList) throws Exception {
		return partDao.insertAndUpdatePartList(partList);
	}

	@Override
	public PartList findPartListById(Integer id) throws Exception {
		return partDao.findPartListById(id);
	}

	/**
	 * 批量插入
	 */
	@Override
	public boolean batchAdd(Integer deviceListId, String partIds)
			throws Exception {
		if(StringUtil.isNotEmpty(partIds))
		{
			String[] ids = partIds.split(",");
			
			for(int i=0; i< ids.length;i++)
			{
				PartList partList = new PartList();
				partList.setDeviceListId(deviceListId);
				partList.setPartId(Integer.parseInt(ids[i]));
				partList.setStatus((short)1);
				
				partDao.insertAndUpdatePartList(partList);
			}
			
		}

		return true;
	}

	@Override
	public List<PartListDTO> findPartListByDevice(Integer deviceListId)
			throws Exception {
		return partDao.findPartListByDevice(deviceListId);
	}

	@Override
	public Pagination<PartListDTO> queryPartListByPage(PartListQueryDTO queryDTO)throws Exception {
			
		return partDao.queryPartListByPage(queryDTO);
	}

	
	@Override
	public Pagination<Part> queryPartByPage(PartQueryDTO queryDTO)throws Exception {
			
		return partDao.queryPartByPage(queryDTO);
	}
	@Override
	public List<PartDTO> findAllParts() throws Exception {
		return partDao.findAllParts();
	}

	@Override
	public Boolean deletePartListById(Integer id) throws Exception {
		return partDao.deletePartListById(id);
	}

	
}
