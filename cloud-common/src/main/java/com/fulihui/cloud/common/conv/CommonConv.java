package com.fulihui.cloud.common.conv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommonConv
{
	private static final Logger LOG = LoggerFactory.getLogger(CommonConv.class);
	
	public static void buildExample(Object source, Object target, Object example)
	{
		Field[] fields = null;
		try
		{
			fields = source.getClass().getDeclaredFields();
		}
		catch (Exception e)
		{
			LOG.error("buildExample转换错误：", e);
		}
		if (null != fields && fields.length > 0)
		{
			build(source, target, example, null, null, fields);
		}
	}
	
	private static void sortBuild(StringBuilder sb, Object example)
	{
		if (null == sb || sb.length() == 0
				|| null == example)
		{
			return;
		}
		Class<?> cls = example.getClass();
		try
		{
			Method setMethod = cls.getMethod("setOrderByClause", new Class[] {String.class});
			setMethod.invoke(example, new Object[] {sb.toString()});
		}
		catch (Exception e)
		{
			LOG.error("sortBuild 转换错误：", e);
		}
	}
	
	private static void analysis(String fieldName, StringBuilder sb, String sort)
	{
		if (null == sb)
		{
			return;
		}
		if (sb.length() > 0)
		{
			sb.append(",");
		}
		sb.append(fieldName.substring(0, fieldName.lastIndexOf(sort)));
		sb.append(" ");
		sb.append(sort);
	}
	
	public static void build(Object source, Object target, Object example, String prefix, String suffix, Field[] fields)
	{
		if (null != source && null != target && null != fields)
		{
			Class<?> cls = source.getClass();
			Class<?> ccls = target.getClass();
			String fieldName = null;
			String getMethodName = null;
			String andMethodName = null;
			StringBuilder sb = null;
			StringBuilder csb = null;
			Method getMethod = null;
			Method andMethod = null;
			Object value = null;
			StringBuilder esb = new StringBuilder();
			for (Field field : fields)
			{
				try
				{
					fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName) || "orderBy".equals(fieldName)
							|| "content".equals(fieldName))
					{
						continue;
					}
					sb = new StringBuilder();
					if (null != prefix)
					{
						sb.append(prefix);
					}
					else
					{
						sb.append("and");
					}
					sb.append(fieldName.substring(0, 1).toUpperCase());
					if (fieldName.endsWith("List"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 4));
					}
					else if (fieldName.endsWith("NotIn"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 5));
					}
					else if (fieldName.endsWith("Like"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 4));
					}
					else if (fieldName.endsWith("LessThan"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 8));
					}
					else if (fieldName.endsWith("LessThanOrEqualTo"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 17));
					}
					else if (fieldName.endsWith("GreaterThan"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 11));
					}
					else if (fieldName.endsWith("GreaterThanOrEqualTo"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 20));
					}
					else if (fieldName.endsWith("NotEqualTo"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 10));
					}
					else if (fieldName.endsWith("IsNull"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 6));
					}
					else if (fieldName.endsWith("IsNotNull"))
					{
						sb.append(fieldName.substring(1, fieldName.length() - 9));
					}					
					else
					{
						sb.append(fieldName.substring(1));
					}
					if (null != suffix)
					{
						sb.append(suffix);
					}
					else if (fieldName.endsWith("List"))
					{
						sb.append("In");
					}
					else if (fieldName.endsWith("NotIn"))
					{
						sb.append("NotIn");
					}
					else if (fieldName.endsWith("Like"))
					{
						sb.append("Like");
					}
					else if (fieldName.endsWith("LessThan"))
					{
						sb.append("LessThan");
					}
					else if (fieldName.endsWith("LessThanOrEqualTo"))
					{
						sb.append("LessThanOrEqualTo");
					}
					else if (fieldName.endsWith("GreaterThan"))
					{
						sb.append("GreaterThan");
					}
					else if (fieldName.endsWith("GreaterThanOrEqualTo"))
					{
						sb.append("GreaterThanOrEqualTo");
					}
					else if (fieldName.endsWith("NotEqualTo"))
					{
						sb.append("NotEqualTo");
					}
					else if (fieldName.endsWith("IsNull"))
					{
						sb.append("IsNull");
					}
					else if (fieldName.endsWith("IsNotNull"))
					{
						sb.append("IsNotNull");
					}					
					else
					{
						sb.append("EqualTo");
					}
					andMethodName = sb.toString();
					if (fieldName.endsWith("IsNull") || fieldName.endsWith("IsNotNull"))
					{
						andMethod = ccls.getMethod(andMethodName, new Class[] {});
						andMethod.invoke(target, new Object[] {});
					}
					else
					{
						csb = new StringBuilder();
						csb.append("get");
						csb.append(fieldName.substring(0, 1).toUpperCase());
						csb.append(fieldName.substring(1));
						getMethodName = csb.toString();
						getMethod = cls.getMethod(getMethodName, new Class[] {});
						if (fieldName.endsWith("ASC") || fieldName.endsWith("DESC"))
						{
							//不进行处理
						}
						else
						{
							andMethod = ccls.getMethod(andMethodName, new Class[] { field.getType() });
						}
						value = getMethod.invoke(source, new Object[] {});
						if (null != value)
						{
							if (fieldName.endsWith("ASC"))
							{
								analysis(fieldName, esb, "ASC");
							}
							else if (fieldName.endsWith("DESC"))
							{
								analysis(fieldName, esb, "DESC");
							}
							else
							{
								if (fieldName.endsWith("Like"))
								{
									value = "%" + value + "%";
								}
								andMethod.invoke(target, new Object[] { value });
							}
						}
					}
				}
				catch (Exception e)
				{
					LOG.error("build转换错误：", e);
				}
			}
			sortBuild(esb, example);
		}
	}
}

