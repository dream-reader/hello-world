package com.fulihui.cloud.common.enums;

/**
 * 
 * @author ws
 * 
 * 返回码枚举
 *
 */
public enum ErrorEnum
{
	SUCCESS("00000000", "请求成功！"), 
	SYSTEM_ERROR("00000001", "系统错误！"),
	REPAIR_ERROR("00001000", "系统错误，系统正在维护中！"),
	BUSY_ERROR("00001001", "系统繁忙，请稍后再试！"),
	PARAM_ERROR("00001002", "参数错误");
	
	private ErrorEnum(String code, String message)
	{
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 错误码
	 */
	private String code;
	
	/**
	 * 错误描述信息
	 */
	private String message;
	
	public String getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
