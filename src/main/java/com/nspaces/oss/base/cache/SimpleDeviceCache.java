package com.nspaces.oss.base.cache;

import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;

public class SimpleDeviceCache {

	private static SimpleDeviceCache instance = null;

	private static Cache<Integer,DeviceCacheDTO> cacheDevice = new CacheImpl<Integer,DeviceCacheDTO>("device_cache");
	
	private SimpleDeviceCache() {
	}

	public static SimpleDeviceCache getInstance() {
		if (instance == null)
			instance = new SimpleDeviceCache();
		return instance;
	}

	
	public static Cache<Integer,DeviceCacheDTO> getCacheDevice() {
		return cacheDevice;
	}

	public static void setCacheDevice(Cache<Integer,DeviceCacheDTO> cacheDevice) {
		SimpleDeviceCache.cacheDevice = cacheDevice;
	}

	
}
