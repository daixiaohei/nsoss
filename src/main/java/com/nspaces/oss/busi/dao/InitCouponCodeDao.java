package com.nspaces.oss.busi.dao;


import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.busi.domain.CouponCode;
import com.nspaces.oss.busi.dto.CouponCodeDTO;
@Repository
public class InitCouponCodeDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(InitCouponCodeDao.class);
	
	private String tableName = "coupon_code";//主表
	private String idColumnName = "id";

	
	
	public void init() throws Exception{

		for(int i=0;i<200;i++){
			CouponCode code = new CouponCode();
			StringBuffer sb = new StringBuffer();
			sb.append("BXFD2").append(getRandom());
			code.setCodeType("1");
			code.setCouponId(1);
			code.setCodeNo(sb.toString());
			code.setStatus("0");
			code.setCouponMoney("40");

			super.insert(code, tableName, "");
		}


		
		
	}
	
	public static String getRandom(){
		int a = (int)Math.floor(Math.random()*10);
		int b = (int)Math.floor(Math.random()*10);
		int c = (int)Math.floor(Math.random()*10);
		int d = (int)Math.floor(Math.random()*10);
		int e = (int)Math.floor(Math.random()*10);
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(a).append(b).append(c).append(d).append(e);
		System.out.println(sb.toString());
		return sb.toString();
		
	
		
	}


}
