package com.nspaces.oss.busi.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.BusiGroup;
import com.nspaces.oss.busi.domain.BusiGroupDevice;
import com.nspaces.oss.busi.domain.BusiGroupRelated;
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.dto.BusiGroupDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceQueryDTO;
import com.nspaces.oss.busi.dto.BusiGroupQueryDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedQueryDTO;
import com.nspaces.oss.busi.dto.BusinessDTO;
import com.nspaces.oss.busi.dto.BusinessQueryDTO;
import com.nspaces.oss.busi.service.BusiGroupDeviceService;
import com.nspaces.oss.busi.service.BusiGroupRelatedService;
import com.nspaces.oss.busi.service.BusiGroupService;
import com.nspaces.oss.busi.service.BusinessService;

@Controller
@RequestMapping("/business")
public class BusinessController {


	public static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

	@Autowired
	public BusinessService businessService;

	@Autowired
	public BusiGroupService busiGroupService;
	
	@Autowired
	public BusiGroupDeviceService busiGroupDeviceService;
	
	@Autowired
	public BusiGroupRelatedService busiGroupRelatedService;
	/**
	 * 删除业务
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteBusiness", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteBusiness(Integer id){
		
		try {
			return businessService.deleteBusiness(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error delete by id");
		}
		return false;
	}

    /**
     * 业务分页查询
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="queryBusiness",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<BusinessDTO> queryBusiness(BusinessQueryDTO queryDTO){
		
		logger.debug("beging to queryBusiness--");
		try {
			return  businessService.queryBusinessByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryBusiness");
		}
		return null;
	}
	

    /**
     * 业务的添加和修改
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="editBusiness",method=RequestMethod.POST)
	@ResponseBody
	public Business editBusiness(@RequestBody Business business){
		
		logger.debug("beging to editBusiness--");
		logger.info("editBusiness: "+business.toString());
		try {
			return businessService.editBusiness(business);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save editBusiness");
		}
		return null;
	}
	
	
	/**
	 * 删除业务分组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteBusiGroup", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteBusiGroup(Integer id){
		
		try {
			return busiGroupService.deleteBusiGroup(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error delete by id");
		}
		return false;
	}

    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="queryBusiGroup",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<BusiGroupDTO> queryBusiGroup(BusiGroupQueryDTO queryDTO){
		
		logger.debug("beging to queryBusiGroup--");
		try {
			return  busiGroupService.queryBusiGroupByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryBusiGroup");
		}
		return null;
	}
	

    /**
     * 添加和修改业务分组
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="editBusiGroup",method=RequestMethod.POST)
	@ResponseBody
	public BusiGroup editBusiGroup(@RequestBody BusiGroup busiGroup){
		
		logger.debug("beging to editBusiGroup--");
		logger.info("editBusiGroup: "+busiGroup.toString());
		try {
			return busiGroupService.editBusiGroup(busiGroup);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save editBusiGroup");
		}
		return null;
	}
	
	
	
	/**
	 * 新增or编辑 分组关联业务信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "editBusiGroupRelated", method = RequestMethod.POST)
	@ResponseBody
	public BusiGroupRelated editBusiGroupRelated(@RequestBody BusiGroupRelated busiGroupRelated) {
		logger.info("editBusiGroupRelated");
		try {
			return busiGroupRelatedService.editBusiGroupRelated(busiGroupRelated);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error BusiGroupRelated");
		}
		return null;
	}

	/**
	 * 删除分组关联业务信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteBusiGroupRelated", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBusiGroupRelated(Integer id) {
		
		logger.info(" delete deleteBusiGroupRelated id:" + id);
		try {

			busiGroupRelatedService.deleteBusiGroupRelated(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteBusiGroupRelated  " + e.getMessage());
		}
	}
	
	/**
	 * 查询分组关联业务信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "queryBusiGroupRelated", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<BusiGroupRelatedDTO> queryBusiGroupRelated(BusiGroupRelatedQueryDTO queryDTO) {
		logger.info("queryBusiGroupRelated by page");
		try {
			return busiGroupRelatedService.queryBusiGroupRelatedByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryBusiGroupRelated by page");
		}
		return null;
	}

	/**
	 * 分组关联业务     业务分组名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findGroupName", method = RequestMethod.GET)
	@ResponseBody
	public List<BusiGroupRelatedDTO> findBusiGroupName() {
		logger.info("findBusiGroupName");
		try {
			List<BusiGroupRelatedDTO> list = busiGroupRelatedService.findBusiGroupName();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findBusiGroupName");
		}

		return null;
	}
	
	/**
	 * 分组关联业务    业务名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findBusiName", method = RequestMethod.GET)
	@ResponseBody
	public List<BusiGroupRelatedDTO> findBusiName() {
		logger.info("findBusiName");
		try {
			List<BusiGroupRelatedDTO> list = busiGroupRelatedService.findBusiName();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findBusiName");
		}

		return null;
	}
	
	
	/**
	 * 新增or编辑 分组关联业务信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "editBusiGroupDevice", method = RequestMethod.POST)
	@ResponseBody
	public BusiGroupDevice editBusiGroupDevice(@RequestBody BusiGroupDevice busiGroupDevice) {
		logger.info("editBusiGroupDevice");
		try {
			return busiGroupDeviceService.editBusiGroupDevice(busiGroupDevice);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error BusiGroupDevice");
		}
		return null;
	}

	/**
	 * 删除业务分组设备信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteBusiGroupDevice", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBusiGroupDevice(Integer id) {
		
		logger.info(" delete deleteBusiGroupDevice id:" + id);
		try {

			busiGroupDeviceService.deleteBusiGroupDevice(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteBusiGroupDevice  " + e.getMessage());
		}
	}
	
	/**
	 * 查询业务分组设备信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "queryBusiGroupDevice", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<BusiGroupDeviceDTO> queryBusiGroupDevice(BusiGroupDeviceQueryDTO queryDTO) {
		logger.info("queryBusiGroupDevice by page");
		try {
			return busiGroupDeviceService.queryBusiGroupDeviceByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryBusiGroupDevice by page");
		}
		return null;
	}

	/**
	 * 业务分组设备     业务分组名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findBusiGroupName", method = RequestMethod.GET)
	@ResponseBody
	public List<BusiGroupDeviceDTO> findGroupName() {
		logger.info("findBusiGroupName");
		try {
			List<BusiGroupDeviceDTO> list = busiGroupDeviceService.findBusiGroupName();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findBusiGroupName");
		}

		return null;
	}
	
	/**
	 * 业务分组设备    设备名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findDeviceListName", method = RequestMethod.GET)
	@ResponseBody
	public List<BusiGroupDeviceDTO> findDeviceListName() {
		logger.info("findDeviceListName");
		try {
			List<BusiGroupDeviceDTO> list = busiGroupDeviceService.findDeviceListName();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findDeviceListName");
		}

		return null;
	}
}
