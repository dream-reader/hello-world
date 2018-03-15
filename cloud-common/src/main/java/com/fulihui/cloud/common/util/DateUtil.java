package com.fulihui.cloud.common.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public final class DateUtil
{
	private static final Logger LOGGER = Logger.getLogger(DateUtil.class);

	public static Date dayBegin(Date date)
	{
		Calendar cl = Calendar.getInstance();
		if (!Objects.isNull(date))
		{
			cl.setTime(date);
		}
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}
	
	public static Date yearBegin(Date date)
	{
		Calendar cl = Calendar.getInstance();
		if (!Objects.isNull(date))
		{
			cl.setTime(date);
		}
		cl.set(Calendar.MONTH, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}
	
	public static Date yearEnd(Date date)
	{
		Calendar cl = Calendar.getInstance();
		if (!Objects.isNull(date))
		{
			cl.setTime(date);
		}
		cl.set(Calendar.MONTH, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		cl.add(Calendar.MONTH, 12);
		return cl.getTime();
	}
	
	
	public static Date beforeNDay(Date date, int day)
	{
		Calendar cl = Calendar.getInstance();
		if (!Objects.isNull(date))
		{
			cl.setTime(date);
		}
		cl.add(Calendar.DAY_OF_MONTH, -day);
		return cl.getTime();
	}
	
	public static Date afterNDay(Date date, int day)
	{
		Calendar cl = Calendar.getInstance();
		if (!Objects.isNull(date))
		{
			cl.setTime(date);
		}
		cl.add(Calendar.DAY_OF_MONTH, day);
		return cl.getTime();
	}
	
	public static String format(Date date, String format)
	{
		if (Objects.isNull(date))
		{
			return null;
		}
		
		if (Objects.isNull(format))
		{
			SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return defaultFormat.format(date);
		}
		else
		{
			SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
			return simpleFormat.format(date);
		}
	}
	
	public static Date parse(String dateStr, String format)
	{
		if (StringUtil.isNullOrEmpty(dateStr))
		{
			return null;
		}
		try
		{
			if (Objects.isNull(format))
			{
				SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return defaultFormat.parse(dateStr);
			}
			else
			{
				SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
				return simpleFormat.parse(dateStr);
			}
		}
		catch (Exception e)
		{
			LOGGER.error("date parse error : {}", e);
		}
		return null;
	}
	
	public static int get(int type, Date date)
	{
		Calendar cl = Calendar.getInstance();
		if (Objects.isNull(date))
		{
			return cl.get(type);
		}
		else
		{
			cl.setTime(date);
			return cl.get(type);
		}
	}
}
