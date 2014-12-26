package com.nspaces.oss.mcs.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.nspaces.oss.base.dao.MongoBaseDAO;
import com.nspaces.oss.mcs.domain.DeviceLog;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;
import org.springframework.data.domain.Sort.Order;

@Repository
public class DeviceMongoBaseDAO extends MongoBaseDAO {

	public Query filter() {
		return new Query(Criteria.where("status").is(1));
	}
	
	public static  List<Order> getAllOrder()
	{
		List<Order>  tm = new ArrayList<Order>();
		tm.add(new Order(Direction.DESC, "createAt"));
		tm.add(new Order(Direction.ASC, "level"));
		
		return tm;
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
	
	public void insertDeviceLog(DeviceLog deviceLog)
	{
		super.insert(deviceLog);
	}
	
	public void updateDeviceLog(Integer deviceListId)
	{
		Query query = new Query(Criteria.where("deviceListId").in(deviceListId).and("status").in(0));
		super.updateMultiObject(query, new Update().inc("status", 1), DeviceLog.class);
	}
	
	//按网点查询
	public List<DeviceLog> findAllByDeviceLog(List<Integer> deviceListIds, long dateTime, int level)
	{
		Query query;
		if(level == 0)
		   query = new Query(Criteria.where("deviceListId").in(deviceListIds).and("createAt").gt(dateTime).and("status").lte(0)).with(new Sort(new Sort.Order(Direction.ASC, "deviceListId"))).with(new Sort(getAllOrder())) ;
		else
			query = new Query(Criteria.where("deviceListId").in(deviceListIds).and("createAt").gt(dateTime).and("level").gte(level).and("status").lte(0)).with(new Sort(getAllOrder()));
		
		System.out.println(query.toString());
		return super.findAllByQuery(query, DeviceLog.class);
	}
	
	//查询所有网点
	public List<DeviceLog> findAllByDeviceLogEx(long dateTime, int level)
	{
		Query query = new Query(Criteria.where("createdAt").gt(dateTime));
		
		if(level == 0)
			   query = new Query(Criteria.where("createdAt").gt(dateTime).and("status").lte(0)).with(new Sort(new Sort.Order(Direction.ASC, "deviceListId"))).with(new Sort(getAllOrder()));
			else
				query = new Query(Criteria.where("createdAt").gt(dateTime).and("level").gte(level).and("status").lte(0)).with(new Sort(new Sort.Order(Direction.ASC, "deviceListId"))).with(new Sort(getAllOrder()));
			
		
		System.out.println(query.toString());
		return super.findAllByQuery(query, DeviceLog.class);
	}
	
	public List<DeviceMcs> findAllBy(List<Integer> deviceListIds, long dateTime)
	{
		Query query = new Query(Criteria.where("deviceListId").in(deviceListIds).and("createdAt").gt(dateTime));
		
		System.out.println(query.toString());
		return super.findAllByQuery(query, DeviceMcs.class);
	}
}
