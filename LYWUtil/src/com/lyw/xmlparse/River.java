package com.lyw.xmlparse;

import java.io.Serializable;

public class River implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String name;
	private String length;
	private String introduction;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

}
