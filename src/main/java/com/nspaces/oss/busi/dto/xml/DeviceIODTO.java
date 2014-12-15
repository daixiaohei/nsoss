package com.nspaces.oss.busi.dto.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceIODTO {
	
	
	private Integer deviceListId;
	
	private String ipAddr;//机器名字
	
	private String remoteAddr;//远程地址
	
	private String deviceName;//机器名称
	
	private String cpu;//CPU 类型
	
	private Integer partCount;//子设备数量
	
	
	private List<PartDTO> partDTO;//列出所有的I/O 配置

	@XmlElement
	public Integer getDeviceListId() {
		return deviceListId;
	}

	public void setDeviceListId(Integer deviceListId) {
		this.deviceListId = deviceListId;
	}

	@XmlElement
	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@XmlElement
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@XmlElement
	public Integer getPartCount() {
		return partCount;
	}

	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}

	@XmlElement
	public List<PartDTO> getPartDTO() {
		return partDTO;
	}

	public void setPartDTO(List<PartDTO> partDTO) {
		this.partDTO = partDTO;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	
}
