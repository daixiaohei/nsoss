package com.nspaces.oss.mcs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.nspaces.oss.base.mongo.MongoBase;

@Document(collection="device_mcs")
public class DeviceMcs extends MongoBase {

	private Integer deviceListId;
	
	private long createdAt;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdTime;
	
	private List<PortData> datas = new ArrayList<PortData>();
	
	public Integer getDeviceListId() {
		return deviceListId;
	}
	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}
	
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public List<PortData> getDatas() {
		return datas;
	}
	public void setDatas(List<PortData> datas) {
		this.datas = datas;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
