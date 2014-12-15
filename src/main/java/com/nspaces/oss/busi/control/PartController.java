package com.nspaces.oss.busi.control;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Part;
import com.nspaces.oss.busi.domain.PartList;
import com.nspaces.oss.busi.dto.PartListDTO;
import com.nspaces.oss.busi.dto.PartListQueryDTO;
import com.nspaces.oss.busi.dto.PartQueryDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;
import com.nspaces.oss.busi.service.PartService;

@Controller
@RequestMapping("/part")
public class PartController {

	public static final Logger logger = LoggerFactory.getLogger(PartController.class);

	@Autowired
	public PartService partService;
	
	/**
	 * 增加或者保存通用设备
	 * @param part
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="editpart", method=RequestMethod.POST)
	@ResponseBody
    public Part editPart(@RequestBody Part part) throws Exception
    {
    	logger.info("add part ");
    	try
    	{
    		return partService.insertAndUpdatePart(part);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("add part error" + e.getMessage());
    	}
    	return null;
    }
	
    
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="queryPartByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Part> queryPartByPage(PartQueryDTO queryDTO){
		
		logger.debug("beging to queryPartByPage--");
		try {
			return  partService.queryPartByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryPartByPage");
		}
		return null;
	}
	/**
	 * 查找单个的设备
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findpart", method=RequestMethod.GET)
	@ResponseBody
	public Part findPartById(Integer id) throws Exception
	{
		logger.info("find part by Id " + id);
    	try
    	{
    		return partService.findPartById(id);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("find part error" + e.getMessage());
    	}
    	return null;
	}
	
	@RequestMapping(value="findtype", method=RequestMethod.GET)
	@ResponseBody
	public List<Part> findPartByPartType(Integer partType) throws Exception
	{
		logger.info("find part by parttype " + partType);
    	try
    	{
    		return partService.findPartByPartType(partType);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("find part type error" + e.getMessage());
    	}
    	return null;
	}
	
	/**
	 * 新增、或编辑设备列表明细
	 * @param partList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="editPartList", method=RequestMethod.POST)
	@ResponseBody
	public PartList editPartList(@RequestBody PartList partList) throws Exception
	{
		logger.info("edit partlist " );
    	try
    	{
    		return partService.insertAndUpdatePartList(partList);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("edit partlist error" + e.getMessage());
    	}
    	return null;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deletePartListById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return partService.deletePartListById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deletePartListById ");
		}
		return false;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findPartList", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<PartList> findPartListById(Integer id) throws Exception
	{
		logger.info("find partlist " + id );
		Pagination<PartList> pagination = new Pagination<PartList>();
    	try
    	{
    		PartList p = partService.findPartListById(id);
    		List<PartList> items = new ArrayList<PartList>();
    		items.add(p);
    		pagination.setItems(items);
    		return pagination;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("find partlist error" + e.getMessage());
    	}
    	return null;
	}
	
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="queryPartListByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<PartListDTO> queryPartListByPage(PartListQueryDTO queryDTO){
		
		logger.debug("beging to queryPartListByPage--");
		try {
			return  partService.queryPartListByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryPartListByPage");
		}
		return null;
	}
	
	
	/**
	 * 根据通用设备ID，批量新增设备列表明细
	 * @param partList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="batchAdd", method=RequestMethod.POST)
	@ResponseBody
	public boolean batchAdd(Integer deviceListId, String partIds) throws Exception
	{
		logger.info("batch add partlist " );
    	try
    	{
    		return partService.batchAdd(deviceListId, partIds);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("batch add error" + e.getMessage());
    	}
    	return true;
	}
	
	/**
	 * 根据机器ID，查询所有的设备清单
	 * @param deviceListId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findall", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<PartListDTO> findPartListByDevice(Integer deviceListId) throws Exception
	{
		logger.info("find partlist by Device List Id " + deviceListId );
    	try
    	{	Pagination<PartListDTO> pagination = new Pagination<PartListDTO>();
    		
    		List<PartListDTO> items = partService.findPartListByDevice(deviceListId);
    		pagination.setItems(items);
    		return pagination;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("find partlist by Device List error" + e.getMessage());
    	}
    	return null;
	}
	
	/**
	 * 查询所有的设备清单
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findallParts", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<PartDTO> findallParts() throws Exception
	{
		logger.info("find findallParts");
    	try
    	{	Pagination<PartDTO> pagination = new Pagination<PartDTO>();
    		
    		List<PartDTO> items = partService.findAllParts();
    		pagination.setItems(items);
    		return pagination;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		logger.error("find findallParts error" + e.getMessage());
    	}
    	return null;
	}
}
