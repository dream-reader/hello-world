package com.fulihui.cloud.common.util;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

public final class StringUtil
{
	public static boolean isNull(String str)
	{
		return Objects.isNull(str);
	}
	
	public static boolean isNotNull(String str)
	{
		return Objects.nonNull(str);
	}
	
	public static boolean isEmpty(String str)
	{
		return isNotNull(str) && str.length() == 0;
	}
	
	public static boolean isNotEmpty(String str)
	{
		return isNotNull(str) && str.length() > 0;
	}
	
	public static boolean isNullOrEmpty(String str)
	{
		return Strings.isNullOrEmpty(str);
	}
	
	public static boolean isNotNullAndEmpty(String str)
	{
		return !Strings.isNullOrEmpty(str);
	}
	
	public static List<String> split(String str, String separator)
	{
		if (isNotNull(str) && isNotNull(separator))
		{
			List<String> pictures = Lists.newArrayList(
					Splitter.on(separator)
					.trimResults()
					.omitEmptyStrings()
					.split(str));
			return pictures;
		}
		
		return null;
	}
	
	public static String join(Object[] objs, String separator)
	{
		return Joiner.on(Strings.isNullOrEmpty(separator) ? "," : separator).join(objs);
	}
}
