package com.lyw.listener;

import java.util.EventObject;

public class MyEvent extends EventObject
{
	private Object obj;
	private String sName;

	public MyEvent(Object source, String sName)
	{
		super(source);
		this.obj = source;
		this.sName = sName;
	}

	public Object getObj()
	{
		return obj;
	}

	public String getsName()
	{
		return sName;
	}
}
