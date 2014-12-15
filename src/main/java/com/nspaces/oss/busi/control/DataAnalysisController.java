package com.nspaces.oss.busi.control;

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
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.DataDetailDTO;
import com.nspaces.oss.busi.dto.DataGatherDTO;
import com.nspaces.oss.busi.dto.DataQueryDTO;
import com.nspaces.oss.busi.dto.PartListDTO;
import com.nspaces.oss.busi.service.BusiDataService;

@Controller
@RequestMapping("/analysis")
public class DataAnalysisController {

	public static final Logger logger = LoggerFactory
			.getLogger(DataAnalysisController.class);

	@Autowired
	BusiDataService busiDataService;

	/**
	 * 查询业务汇总信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryDataAnalysis", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<DataGatherDTO> queryDataAnalysis( DataQueryDTO dataQueryDTO) {
		logger.info("queryDataAnalysis by page");
		try {
			Pagination<DataGatherDTO> pagination = new Pagination<DataGatherDTO>();
			List<DataGatherDTO> list =  busiDataService.findBusiGatherDTO(dataQueryDTO);
			pagination.setItems(list);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryDataAnalysis by page");
		}
		return null;
	}
	
	/**
	 * 业务汇总    业务名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findBusiName", method = RequestMethod.GET)
	@ResponseBody
	public List<DataGatherDTO> findBusiName() {
		logger.info("findBusiName");
		try {
			List<DataGatherDTO> list = busiDataService.findBusiName();
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
	 * 查询网点业务汇总信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "querySpotBusiGather", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<DataGatherDTO> querySpotBusiGather( DataQueryDTO dataQueryDTO) {
		logger.info("querySpotBusiGather by page");
		try {
			Pagination<DataGatherDTO> pagination = new Pagination<DataGatherDTO>();
			List<DataGatherDTO> list= busiDataService.findSpotBusiGatherDTO(dataQueryDTO);
			pagination.setItems(list);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error querySpotBusiGather by page");
		}
		return null;
	}
	
	/**
	 * 网点业务汇总    网点名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findSpotName", method = RequestMethod.GET)
	@ResponseBody
	public List<DataGatherDTO> findSpotName() {
		logger.info("findSpotName");
		try {
			List<DataGatherDTO> list = busiDataService.findSpotName();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findSpotName");
		}

		return null;
	}
	
	/**
	 * 网点业务汇总    区域编号
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findCityNo", method = RequestMethod.GET)
	@ResponseBody
	public List<DataGatherDTO> findCityNo() {
		logger.info("findCityNo");
		try {
			List<DataGatherDTO> list = busiDataService.findCityNo();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findCityNo");
		}

		return null;
	}
	
	/**
	 * 查询网点业务明细信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "querySpotBusiDetail", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<DataDetailDTO> querySpotBusiDetail( DataQueryDTO dataQueryDTO) {
		logger.info("querySpotBusiDetail by page");
		try {
			Pagination<DataDetailDTO> pagination = new Pagination<DataDetailDTO>();
			List<DataDetailDTO> list= busiDataService.findSpotBusiDetailDTO(dataQueryDTO);
			pagination.setItems(list);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error querySpotBusiDetail by page");
		}
		return null;
	}
	
	/**
	 * 网点业务明细    业务状态
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findBusiStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<DataDetailDTO> findBusiStatus() {
		logger.info("findBusiStatus");
		try {
			List<DataDetailDTO> list = busiDataService.findBusiStatus();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findBusiStatus");
		}

		return null;
	}

}
