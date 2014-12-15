package com.nspaces.oss.busi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.dto.PaginationCondition;
import com.nspaces.oss.base.util.PaginationUtil;
import com.nspaces.oss.base.util.StringUtil;
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.domain.Cooperation;
import com.nspaces.oss.busi.domain.Coupon;
import com.nspaces.oss.busi.domain.CouponBusiness;
import com.nspaces.oss.busi.domain.CouponDevice;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.CouponBusinessDTO;
import com.nspaces.oss.busi.dto.CouponCodeDTO;
import com.nspaces.oss.busi.dto.CouponDTO;
import com.nspaces.oss.busi.dto.CouponDeviceDTO;
import com.nspaces.oss.busi.dto.CouponQueryDTO;

/**
 */
@Repository
public class CouponDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(CouponDao.class);
	
	private String tableName = "coupon";//主表
	private String idColumnName = "id";
	private String tableName1 = "coupon_code";

	/**
	 * 逻辑删除
	 * @param coupon
	 * @return
	 */
	public boolean deleteCouponByLogic(Coupon coupon){
		if(null != coupon.getId()){
			return super.deleteByLogic(tableName, coupon.getId());
		}
		return false;
	}
	
	/**
	 * 删除优惠券绑定网点
	 * @param coupon
	 * @return
	 */
	public boolean deleteCouponDevi(CouponDevice couponDevice){
		if(null != couponDevice.getId()){
			return super.deleteById("coupon_device", couponDevice.getId());
		}
		return false;
	}
	
	/**
	 * 删除优惠券绑定业务
	 * @param coupon
	 * @return
	 */
	public boolean deleteCouponBusi(CouponBusiness couponBusiness){
		if(null != couponBusiness.getId()){
			return super.deleteById("coupon_business", couponBusiness.getId());
		}
		return false;
	}
	
	/**
	 * 加载合作公司
	 * @return
	 */
	public List<Cooperation> loadCoop(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "SELECT u.id,u.name_full as nameFull from cooperation u where u.status > -1";
		List<Cooperation> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<Cooperation>(Cooperation.class));
		
		if(retList!=null && retList.size()>0){
			 return retList;
		}else{
			 return null;
		}
	}
	
	/**
	 * 加载业务
	 * @return
	 */
	public List<Business> loadBusi(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "SELECT u.id,u.name from business u where u.status > 0";
		List<Business> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<Business>(Business.class));
		
		if(retList!=null && retList.size()>0){
			 return retList;
		}else{
			 return null;
		}
	}
	
	/**
	 * 加载网点
	 * @return
	 */
	public List<DeviceList> loadDevi(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "SELECT u.id,u.name from device_list u where u.status > 0";
		List<DeviceList> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<DeviceList>(DeviceList.class));
		
		if(retList!=null && retList.size()>0){
			 return retList;
		}else{
			 return null;
		}
	}
	
	/**
	 * 描述：插入和更新用户信息
	 */
	public Coupon insertAndUpdate(Coupon coupon) throws Exception
	{
		if(null != coupon.getId())
		{
			super.update(coupon, tableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(coupon, tableName, idColumnName);
			coupon.setId(retId);
		}
		
		return coupon;
	}
	
	/**
	 * 描述：插入和更新 CB信息
	 */
	public CouponBusinessDTO insertAndUpdateCB(CouponBusiness couponBusiness) throws Exception
	{
		CouponBusinessDTO cbDTO = new CouponBusinessDTO();
		if(null != couponBusiness.getId()){//更新
			super.update(couponBusiness, "coupon_business", idColumnName);
			cbDTO.setId(couponBusiness.getId());
		}else{//新增
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String countSql = "select count(*) from coupon_business where busi_id=:busiId and coupon_id=:couponId";
			paramMap.put("busiId", couponBusiness.getBusiId());
			paramMap.put("couponId", couponBusiness.getCouponId());
			if(super.isExistObject(countSql, paramMap)){
				cbDTO.setExist(true);
			}else{
				Integer retId = super.insertAndReturnId(couponBusiness, "coupon_business", idColumnName);
				couponBusiness.setId(retId);
				cbDTO.setId(retId);
			}
		}
		
		cbDTO.setBusiId(couponBusiness.getBusiId());
		cbDTO.setCouponId(couponBusiness.getCouponId());
		return cbDTO;
	}
	
	/**
	 * 描述：插入和更新CD信息
	 */
	public CouponDeviceDTO insertAndUpdateCD(CouponDevice couponDevice) throws Exception
	{
		CouponDeviceDTO cdDTO = new CouponDeviceDTO();
		if(null != couponDevice.getId()){
			super.update(couponDevice, "coupon_device", idColumnName);
			cdDTO.setId(couponDevice.getId());
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String countSql = "select count(*) from coupon_device where device_list_id=:deviId and coupon_id=:couponId";
			paramMap.put("deviId", couponDevice.getDeviceListId());
			paramMap.put("couponId", couponDevice.getCouponId());
			if(super.isExistObject(countSql, paramMap)){
				cdDTO.setExist(true);
			}else{
				Integer retId = super.insertAndReturnId(couponDevice, "coupon_device", idColumnName);
				couponDevice.setId(retId);
				cdDTO.setId(retId);
			}
		}
		
		cdDTO.setCouponId(couponDevice.getCouponId());
		cdDTO.setDeviceListId(couponDevice.getDeviceListId());
		return cdDTO;
	}
	
	
	private String getQueryCondition(CouponQueryDTO queryDTO)
	{
		  String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName())){
			  strCondition = strCondition + " and u.name like '%" + queryDTO.getName() + "%'";
		  }
		  if(StringUtil.isNotEmpty(queryDTO.getCodeNo())){
			  strCondition = strCondition + " and u.code_no = " + queryDTO.getCodeNo() + "";
		  }
		  if(StringUtil.isNotEmpty(queryDTO.getCouponId())){
			  strCondition = strCondition + " and u.coupon_id = " + queryDTO.getCouponId() + "";
		  }
		  if(StringUtil.isNotEmpty(queryDTO.getStatus())){
			  if(!"9".equals(queryDTO.getStatus())){
				  strCondition = strCondition + " and u.status = " + queryDTO.getStatus() + "";
			  }
		  }
		  if(StringUtil.isNotEmpty(queryDTO.getBusiName())){
			  strCondition = strCondition + " and b.name like '%" + queryDTO.getBusiName() + "%'";
		  }
		  if(StringUtil.isNotEmpty(queryDTO.getDeviName())){
			  strCondition = strCondition + " and b.name like '%" + queryDTO.getDeviName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 查询优惠券列表
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<CouponDTO> queryCouponByPage(CouponQueryDTO queryDTO){
	
		logger.debug("start Coupon query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select   ")
			.append("  u.id,u.coop_id as coopId,u.name,u.start_date as startDate,u.end_date as endDate,u.bind_bank as bindBank,  ")
			.append("  case u.coupon_type when 0 then '无券号' when 1 then '有券号' end as couponType, ")
			.append("  case u.bind_type when 0 then '无绑定' when 1 then '绑定业务' when 2 then '绑定银行卡' end as bindType, ")
			.append("  c.name_full as coopName ")
			.append("  from coupon as u ")
			.append("  left join cooperation as c on c.id = u.coop_id")
		    .append(" where u.status > -1 " + getQueryCondition(queryDTO) + " ");
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from coupon as u ")
				 .append(" where  u.status > -1 " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<CouponDTO> result = new Pagination<CouponDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<CouponDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<CouponDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<CouponDTO>(CouponDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	
	/**
	 * 查询优惠券代码详细
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<CouponCodeDTO> queryCouponCodeByPage(CouponQueryDTO queryDTO){
	
		logger.debug("start Coupon query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select   ")
			.append("  u.id,u.code_type as codeType,c.name as couponName,u.coupon_id as couponId,u.code_no as codeNo,u.print_date as printDate,u.use_date as useDate,u.coupon_money as couponMoney,  ")
			.append("  case u.status when 0 then '未使用' when -1 then '已使用' when 1 then '已送出' end as status ")
			.append("  from coupon_code as u ")
			.append("  inner join coupon as c on c.id = u.coupon_id")
		    .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from coupon_code as u ")
		         .append("  inner join coupon as c on c.id = u.coupon_id")
				 .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<CouponCodeDTO> result = new Pagination<CouponCodeDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<CouponCodeDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<CouponCodeDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<CouponCodeDTO>(CouponCodeDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	/**
	 * 查询优惠券代码绑定业务
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<CouponBusinessDTO> queryCouponBusinessByPage(CouponQueryDTO queryDTO){
	
		logger.debug("start Coupon query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select   ")
			.append("  u.id,c.name as couponName,u.coupon_id as couponId,b.name as busiName,u.busi_id as busiId  ")
			.append("  from coupon_business as u ")
			.append("  inner join coupon as c on c.id = u.coupon_id")
			.append("  inner join business as b on b.id = u.busi_id")
		    .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from coupon_business as u ")
		         .append("  inner join coupon as c on c.id = u.coupon_id")
		         .append("  inner join business as b on b.id = u.busi_id")
				 .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());

		System.out.println("sql==>:"+querySQL);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<CouponBusinessDTO> result = new Pagination<CouponBusinessDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<CouponBusinessDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<CouponBusinessDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<CouponBusinessDTO>(CouponBusinessDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	
	/**
	 * 查询优惠券代码绑定设备
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<CouponDeviceDTO> queryCouponDeviceByPage(CouponQueryDTO queryDTO){
	
		logger.debug("start Coupon query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select   ")
			.append("  u.id,c.name as couponName,u.coupon_id as couponId,b.name as deviName,u.device_list_id as deviceListId  ")
			.append("  from coupon_device as u ")
			.append("  inner join coupon as c on c.id = u.coupon_id")
			.append("  inner join device_list as b on b.id = u.device_list_id")
		    .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from coupon_device as u ")
		         .append("  inner join coupon as c on c.id = u.coupon_id")
		         .append("  inner join device_list as b on b.id = u.device_list_id")
				 .append(" where  1 = 1 " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<CouponDeviceDTO> result = new Pagination<CouponDeviceDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<CouponDeviceDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<CouponDeviceDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<CouponDeviceDTO>(CouponDeviceDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	public List<CouponCodeDTO> findCodeNo(String codeNo) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id,status from coupon_code as c where c.code_no= '"+codeNo+"'";
		List<CouponCodeDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<CouponCodeDTO>(CouponCodeDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	/**
	 * 更新优惠券代码状态
	 * 
	 * qt  创建于 2014年7月17日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateCodeNoStatus(Integer id) throws Exception
	{
		if(null!=id){
		return super.deleteByLogicCoupon(tableName1, id);
		}
		return false;
	
	}
	
}
