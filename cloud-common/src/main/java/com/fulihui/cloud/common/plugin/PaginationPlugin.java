package com.fulihui.cloud.common.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class PaginationPlugin extends PluginAdapter
{

	public boolean validate(List<String> arg0)
	{
		return true;
	}

	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
	{
		addLimit(topLevelClass, introspectedTable, "limitStart");

		addLimit(topLevelClass, introspectedTable, "limitEnd");

		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name)
	{
		CommentGenerator commentGenerator = context.getCommentGenerator();

		Field field = new Field();

		field.setVisibility(JavaVisibility.PROTECTED);

		field.setType(PrimitiveTypeWrapper.getIntegerInstance());

		field.setName(name);

		//field.setInitializationString("-1");

		commentGenerator.addFieldComment(field, introspectedTable);

		topLevelClass.addField(field);

		char c = name.charAt(0);

		String camel = Character.toUpperCase(c) + name.substring(1);

		Method method = new Method();

		method.setVisibility(JavaVisibility.PUBLIC);

		method.setName("set" + camel);

		method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));

		method.addBodyLine("this." + name + "=" + name + ";");

		commentGenerator.addGeneralMethodComment(method, introspectedTable);

		topLevelClass.addMethod(method);

		method = new Method();

		method.setVisibility(JavaVisibility.PUBLIC);

		method.setReturnType(PrimitiveTypeWrapper.getIntegerInstance());

		method.setName("get" + camel);

		method.addBodyLine("return " + name + ";");

		commentGenerator.addGeneralMethodComment(method, introspectedTable);

		topLevelClass.addMethod(method);
	}

	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{

		// LIMIT5,10; // 检索记录行 6-15

		XmlElement isNotNullElement = new XmlElement("if");//$NON-NLS-1$

		isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart >= 0"));

		isNotNullElement.addElement(new TextElement("limit ${limitStart} , ${limitEnd}"));

		element.addElement(isNotNullElement);

		// LIMIT 5;//检索前 5个记录行

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);

	}
}
