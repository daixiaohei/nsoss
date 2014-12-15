package com.nspaces.oss.busi.dto.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class DeviceCacheDTO implements Serializable{
	
    @Override
	public String toString() {
		return "DeviceCacheDTO [deviceListId=" + deviceListId + ", name="
				+ name + ", ipAddr=" + ipAddr + ", remoteAddr=" + remoteAddr
				+ ", partCount=" + partCount + ", createDate=" + createDate
				+ ", mapPart=" + mapPart + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer deviceListId;
	private String name;//名字
	private String ipAddr;//机器名字
	private String remoteAddr;//远程地址
	private Integer partCount;//子设备数量
	private String createDate; //创建时间
	
	private HashMap<String, PartDTO> mapPart = new HashMap<String, PartDTO>();
	
	public Integer getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Integer getPartCount() {
		return partCount;
	}

	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}

	public HashMap<String, PartDTO> getMapPart() {
		return mapPart;
	}

	public void setMapPart(HashMap<String, PartDTO> mapPart) {
		this.mapPart = mapPart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
}
