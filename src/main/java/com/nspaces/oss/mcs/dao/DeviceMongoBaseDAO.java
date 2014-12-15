package com.nspaces.oss.mcs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObjectBuilder;
import com.nspaces.oss.base.dao.MongoBaseDAO;
import com.nspaces.oss.base.util.DateUtil;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;

@Repository
public class DeviceMongoBaseDAO extends MongoBaseDAO {

	public Query filter() {
		return new Query(Criteria.where("status").is(1));
	}
	

	public Query getPageQuery(int page, int pageSize) {
		Query query = filter();
		
		if (page == 0) {
			page = 1;
		}
		
		List<Sort.Order> listOrder = new ArrayList<Sort.Order>();
		/*listOrder.add(new Sort.Order(Sort.Direction.ASC,"errorStatus"));
		listOrder.add(new Sort.Order(Sort.Direction.ASC,"errorTimes"));*/
		
		Query curQuery = query.with(new Sort(listOrder));
		curQuery.skip((page - 1) * pageSize);
		curQuery.limit(pageSize);
		return curQuery;
	}

	
	public  void insertDeviceMcs(DeviceMcs deviceMcs)
	{
		super.insert(deviceMcs);
	}
	
	public void insertDeviceOut(DeviceOut deviceOut)
	{
		super.insert(deviceOut);
	}
	
	public List<DeviceMcs> findAllBy(List<Integer> deviceListIds, long dateTime)
	{
		Query query = new Query(Criteria.where("deviceListId").in(deviceListIds).and("createdAt").gt(dateTime));
		
		System.out.println(query.toString());
		return super.findAllByQuery(query, DeviceMcs.class);
	}
}
