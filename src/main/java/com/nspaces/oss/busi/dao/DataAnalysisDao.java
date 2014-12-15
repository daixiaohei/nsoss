package com.nspaces.oss.busi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.base.util.StringUtil;
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.DataDetailDTO;
import com.nspaces.oss.busi.dto.DataGatherDTO;
import com.nspaces.oss.busi.dto.DataQueryDTO;

@Repository
public class DataAnalysisDao extends BaseDAO {

	/**
	 * 查询业务汇总信息
	 * 
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findBusiGatherDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sbd = new StringBuffer();

		sbd.append(" select                                                                                                                                                                                          ");
		sbd.append(" ppt.bs_name as busi_name, sum(IF((ppt.pos_status =1 and ppt.busi_status=1), p_count, 0)) as success_count, sum(IF((ppt.pos_status =1 and ppt.busi_status=1), p_money, 0)) as success_money, ");
		sbd.append(" sum(IF((ppt.pos_status =1 and ppt.busi_status !=1), p_count, 0)) as cancel_count, sum(IF((ppt.pos_status =1 and ppt.busi_status!=1), p_money, 0)) as cancel_money,               ");
		sbd.append(" sum(IF((ppt.pos_status =-2 and ppt.busi_status!=1), p_count, 0)) as auto_count, sum(IF((ppt.pos_status =-2 and ppt.busi_status!=1), p_money, 0)) as auto_money,                  ");
		sbd.append(" sum(IF((ppt.pos_status =-1 and ppt.busi_status!=1), p_count, 0)) as fail_count, sum(IF((ppt.pos_status =-1 and ppt.busi_status!=1), p_money, 0)) as fail_money                   ");
		sbd.append(" from (                                                                                                                                                           ");
		sbd.append(" select bs.name bs_name, bp.status pos_status, br.status as busi_status, count(br.id) as p_count, sum(bp.pay_money/100) as p_money from busi_record br            ");
		sbd.append(" left join device_list dl  on br.device_list_id = dl.id left join business bs on br.busi_id = bs.id  left join busi_pay_record bp on bp.busi_record_id = br.id    ");
		sbd.append(" where ( bp.error_code not in ('28','29','30','31','26','21') or bp.error_code is null) and br.busi_id != 3                                                       ");

		if (null != dataQueryDTO.getBusiId() && 0 != dataQueryDTO.getBusiId()) {
			sbd.append(" and br.busi_id = :busiId ");
		}
		if ((null != dataQueryDTO.getStartDate() && !dataQueryDTO
				.getStartDate().equals(""))) {
			sbd.append(" and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') >= :startDate                                  ");
		}
		if ((null != dataQueryDTO.getEndDate() && (!dataQueryDTO.getEndDate()
				.equals("")))) {
			sbd.append("and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') <= :endDate                                 ");
		}

		sbd.append("  group by bs_name, bp.status, br.status                                                                                                                          ");
		sbd.append(" ) ppt                                                                                                                                                            ");
		sbd.append(" group by ppt.bs_name ");

		sbd.append(" with rollup ");

		paramMap.put("busiId", dataQueryDTO.getBusiId());
		paramMap.put("busiName", dataQueryDTO.getBusiName());
		paramMap.put("startDate", dataQueryDTO.getStartDate());
		paramMap.put("endDate", dataQueryDTO.getEndDate());

		List<DataGatherDTO> retList = jdbcTemplate.query(sbd.toString(),
				paramMap, new BeanPropertyRowMapper<DataGatherDTO>(
						DataGatherDTO.class));

		if (retList != null && retList.size() > 0) {
			return retList;
		} else {
			return null;
		}
	}

	/**
	 * 业务汇总    业务类型
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findBusiName() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select id as busiId,name as busiName from business";
		List<DataGatherDTO> list = jdbcTemplate.query(sql.toString(), paramMap,
				new BeanPropertyRowMapper<DataGatherDTO>(DataGatherDTO.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	/**
	 * 查询网点业务汇总信息
	 * 
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findSpotBusiGatherDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sbd = new StringBuffer();

		sbd.append(" select                                                                                                                                                                                          ");
		sbd.append(" ppt.ds_name as device_name, ppt.bs_name as busi_name, sum(IF((ppt.pos_status =1 and ppt.busi_status=1), p_count, 0)) as success_count, sum(IF((ppt.pos_status =1 and ppt.busi_status=1), p_money, 0)) as success_money, ");
		sbd.append(" sum(IF((ppt.pos_status =1 and ppt.busi_status !=1), p_count, 0)) as cancel_count, sum(IF((ppt.pos_status =1 and ppt.busi_status!=1), p_money, 0)) as cancel_money,               ");
		sbd.append(" sum(IF((ppt.pos_status =-2 and ppt.busi_status!=1), p_count, 0)) as auto_count, sum(IF((ppt.pos_status =-2 and ppt.busi_status!=1), p_money, 0)) as auto_money,                  ");
		sbd.append(" sum(IF((ppt.pos_status =-1 and ppt.busi_status!=1), p_count, 0)) as fail_count, sum(IF((ppt.pos_status =-1 and ppt.busi_status!=1), p_money, 0)) as fail_money                   ");
		sbd.append(" from (                                                                                                                                                           ");
		sbd.append(" select dl.name as ds_name, bs.name bs_name, bp.status pos_status, br.status as busi_status, count(br.id) as p_count, sum(bp.pay_money/100) as p_money from busi_record br            ");
		sbd.append(" left join device_list dl  on br.device_list_id = dl.id left join business bs on br.busi_id = bs.id  left join busi_pay_record bp on bp.busi_record_id = br.id    ");
		sbd.append(" where ( bp.error_code not in ('28','29','30','31','26','21') or bp.error_code is null) and br.busi_id != 3                                                       ");

		if (null != dataQueryDTO.getBusiId() && 0 != dataQueryDTO.getBusiId()) {
			sbd.append(" and br.busi_id = :busiId ");
		}
		if (null != dataQueryDTO.getDeviceListId()
				&& !dataQueryDTO.getDeviceListId().equals("")) {
			sbd.append(" and br.device_list_id =:deviceListId  ");
		}
		if (StringUtil.isNotEmpty(dataQueryDTO.getCityNo())) {
			sbd.append(" and br.device_list_id in ( select dsl.id from device_list dsl left join spot st on dsl.spot_id = st.id where st.city_no = :cityNo ) ");
		}

		if ((null != dataQueryDTO.getStartDate() && !dataQueryDTO
				.getStartDate().equals(""))) {
			sbd.append(" and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') >= :startDate                                  ");
		}
		if ((null != dataQueryDTO.getEndDate() && (!dataQueryDTO.getEndDate()
				.equals("")))) {
			sbd.append("and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') <= :endDate                                 ");
		}
		// sbd.append(" and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') >= :startDate and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') < :endDate                                 ");
		sbd.append("  group by ds_name, bs_name, bp.status, br.status                                                                                                                          ");
		sbd.append(" ) ppt                                                                                                                                                            ");
		sbd.append(" group by ppt.ds_name, ppt.bs_name ");
		sbd.append(" with rollup ");

		paramMap.put("busiId", dataQueryDTO.getBusiId());
		paramMap.put("busiName", dataQueryDTO.getBusiName());
		paramMap.put("startDate", dataQueryDTO.getStartDate());
		paramMap.put("endDate", dataQueryDTO.getEndDate());
		paramMap.put("deviceListId", dataQueryDTO.getDeviceListId());
		paramMap.put("cityNo", dataQueryDTO.getCityNo());

		List<DataGatherDTO> retList = jdbcTemplate.query(sbd.toString(),
				paramMap, new BeanPropertyRowMapper<DataGatherDTO>(
						DataGatherDTO.class));

		if (retList != null && retList.size() > 0) {
			return retList;
		} else {
			return null;
		}
	}

    /**
     * 网点业务汇总  网点名
     * @return
     * @throws Exception
     */
	public List<DataGatherDTO> findSpotName() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select id as deviceListId,name as deviceName from device_list";
		List<DataGatherDTO> list = jdbcTemplate.query(sql.toString(), paramMap,
				new BeanPropertyRowMapper<DataGatherDTO>(DataGatherDTO.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	
	/**
	 * 网点业务汇总  区域编号
	 * @return
	 * @throws Exception
	 */
	public List<DataGatherDTO> findCityNo() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select city_no as cityNo from spot";
		List<DataGatherDTO> list = jdbcTemplate.query(sql.toString(), paramMap,
				new BeanPropertyRowMapper<DataGatherDTO>(DataGatherDTO.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	/**
	 * 查询网点明细交易信息
	 * 
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<DataDetailDTO> findSpotBusiDetailDTO(DataQueryDTO dataQueryDTO)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sbd = new StringBuffer();
		sbd.append(" select                                                                                                                                                                                          ");
		sbd.append("  br.id as busi_id, dl.name as device_name, bs.name as busi_name,  bp.pay_money /100 as pay_money,case bp.status when 1 then '支付成功' when -1 then '支付失败' when -2 then '已自动退款' else '未插卡操作' end as pay_status, bp.error_code,case br.status when 1 then '正常' else '失败' end as busi_status, br.busi_logic_no, date_format(bp.created_at,'%Y-%m-%d') as pay_time, bp.bank_acct_no, bp.pos_device_no, bp.pos_record_no, date_format(bp.created_at,'%Y-%m-%d %H:%i:%s') as create_time  from busi_record br    ");
		sbd.append(" left join device_list dl  on br.device_list_id = dl.id left join business bs on br.busi_id = bs.id  left join busi_pay_record bp on bp.busi_record_id = br.id    ");
		sbd.append(" where ( bp.error_code not in ('28','29','30','31','26','21') or bp.error_code is null) and br.busi_id != 3                                                       ");

		if (null != dataQueryDTO.getBusiId() && 0 != dataQueryDTO.getBusiId()) {
			sbd.append(" and br.busi_id = :busiId ");
		}

		if (null != dataQueryDTO.getDeviceListId()
				&& !dataQueryDTO.getDeviceListId().equals("")) {
			sbd.append(" and br.device_list_id =:deviceListId  ");
		}
		if (null != dataQueryDTO.getStatusId()
				&& !dataQueryDTO.getStatusId().equals("")) {
			sbd.append(" and br.status = :statusId ");
		}
		if (StringUtil.isNotEmpty(dataQueryDTO.getCityNo())) {
			sbd.append(" and br.device_list_id in ( select dsl.id from device_list dsl left join spot st on dsl.spot_id = st.id where st.city_no = :cityNo ) ");
		}
		// if (StringUtil.isNotEmpty(dataQueryDTO.getCityNo())) {
		// if (null != dataQueryDTO.getDeviceListId()
		// && 0 != dataQueryDTO.getDeviceListId()) {
		// sbd.append(" and br.device_list_id in ( select dsl.id from device_list dsl left join spot st on dsl.spot_id = st.id where st.city_no = :cityNo ) ");
		// } else {
		// sbd.append(" and br.device_list_id =:deviceListId  ");
		// }
		// }

		if ((null != dataQueryDTO.getStartDate() && !dataQueryDTO
				.getStartDate().equals(""))) {
			sbd.append(" and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') >= :startDate                                  ");
		}
		if ((null != dataQueryDTO.getEndDate() && (!dataQueryDTO.getEndDate()
				.equals("")))) {
			sbd.append("and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') <= :endDate                                 ");
		}
		// sbd.append(" and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') >= :startDate and date_format(br.created_at, '%Y-%m-%d %H:%i:%s') < :endDate                                 ");

		paramMap.put("busiId", dataQueryDTO.getBusiId());
		paramMap.put("startDate", dataQueryDTO.getStartDate());
		paramMap.put("endDate", dataQueryDTO.getEndDate());
		paramMap.put("cityNo", dataQueryDTO.getCityNo());
		paramMap.put("deviceListId", dataQueryDTO.getDeviceListId());
		paramMap.put("busiStatus", dataQueryDTO.getBusiStatus());
		paramMap.put("payStatus", dataQueryDTO.getPosStatus());
		paramMap.put("statusId", dataQueryDTO.getStatusId());

		List<DataDetailDTO> retList = jdbcTemplate.query(sbd.toString(),
				paramMap, new BeanPropertyRowMapper<DataDetailDTO>(
						DataDetailDTO.class));

		if (retList != null && retList.size() > 0) {
			return retList;
		} else {
			return null;
		}
	}

	
	/**
	 * 网点明细交易   业务状态
	 * @return
	 * @throws Exception
	 */
	public List<DataDetailDTO> findBusiStatus() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select distinct(br.status) as statusId,case br.status when 1 then '正常' else '失败' end as busiStatus from busi_record as br";
		List<DataDetailDTO> list = jdbcTemplate.query(sql.toString(), paramMap,
				new BeanPropertyRowMapper<DataDetailDTO>(DataDetailDTO.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
