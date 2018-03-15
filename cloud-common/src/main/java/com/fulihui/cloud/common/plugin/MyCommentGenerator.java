package com.fulihui.cloud.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCommentGenerator extends DefaultCommentGenerator
{

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn)
	{
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals(""))
		{
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			// addJavadocTag(field, false);
			field.addJavaDocLine(" */");
		}
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete)
	{
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());
		sb.setLength(0);
		sb.append(" * @author ");
		sb.append(System.getProperty("user.name"));
		sb.append(" ");
		sb.append(format.format(new Date()));
		innerClass.addJavaDocLine(sb.toString());
		innerClass.addJavaDocLine(" */");
	}
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
	{
		StringBuilder sb = new StringBuilder();
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * ");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(" * ");
		topLevelClass.addJavaDocLine(" */");
	}
}
