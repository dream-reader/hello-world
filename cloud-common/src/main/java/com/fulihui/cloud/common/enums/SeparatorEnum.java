package com.fulihui.cloud.common.enums;

public enum SeparatorEnum
{
	COMMA_SYMBOL(","),
	COLON(":");
	
	private SeparatorEnum(String value)
	{
		this.value = value;
	}
	
	private String value;

	public String getValue()
	{
		return value;
	}
}
