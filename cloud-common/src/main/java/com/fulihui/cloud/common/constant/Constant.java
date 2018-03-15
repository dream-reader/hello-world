package com.fulihui.cloud.common.constant;

/**
 * 
 * @author ws
 * 常量类
 */
public interface Constant
{
	/**
	 * 返回结果常量
	 */
	public static interface ResultStatusConstant
	{
		public static final boolean DEFAULT_SUCCESS = true;
		
		public static final boolean DEFAULT_FAILED = false;
	}
	
	
	/**
	 * 版本号常量
	 */
	public static interface VersionConstant
	{
		public static final String DEFAULT_VERSION = "1.0.0";
	}
	
	
	/**
	 * 分页常量
	 */
	public static interface PageRequestConstant
	{
		/**
		 * 默认当前页
		 */
		public static final int DEFAULT_PAGE = 1;
		
		/**
		 * 默认每页条数
		 */
		public static final int DEFAULT_PAGE_SIZE = 10;
	}
	
	/**
	 * 
	 * 设备信息常量
	 *
	 */
	public static interface DeviceConstant
	{
		/**
		 * 默认设备ip
		 */
		public static final String DEFAULT_DEVICE_IP = "0.0.0.0";
		
		/**
		 * 默认设备名
		 */
		public static final String DEFAULT_DEVICE_HOST_NAME = "NONE";
	}

	/**
	 * redis key常量
	 */
	public static interface RedisKeyConstant
	{
		/**
		 * 券码 key
		 */
		public static final String COUPONS_NUMBER_KEY = "COUPONS_PLATFORM:COUPONS_NUMBER:";
	}
}
