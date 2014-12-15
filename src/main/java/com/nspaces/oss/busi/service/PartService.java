package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Part;
import com.nspaces.oss.busi.domain.PartList;
import com.nspaces.oss.busi.dto.PartListDTO;
import com.nspaces.oss.busi.dto.PartListQueryDTO;
import com.nspaces.oss.busi.dto.PartQueryDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;

public interface PartService {
	public Part insertAndUpdatePart(Part part) throws Exception;
	
	public Part findPartById(Integer id) throws Exception;
	
	public List<Part> findPartByPartType(Integer partType) throws Exception;
	
	public PartList insertAndUpdatePartList(PartList partList) throws Exception;
	
	public PartList findPartListById(Integer id) throws Exception;
	
	public boolean batchAdd(Integer deviceListId, String partIds) throws Exception;
	
	public List<PartListDTO> findPartListByDevice(Integer deviceListId) throws Exception;
	
	public Pagination<PartListDTO> queryPartListByPage(PartListQueryDTO queryDTO) throws Exception;
	public Pagination<Part> queryPartByPage(PartQueryDTO queryDTO) throws Exception;
	
	public List<PartDTO> findAllParts() throws Exception; 
	public Boolean deletePartListById(Integer id) throws Exception; 

	


}
