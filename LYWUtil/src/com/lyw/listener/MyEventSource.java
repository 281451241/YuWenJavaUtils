package com.lyw.listener;

import java.util.Iterator;
import java.util.Vector;

public class MyEventSource
{
	private Vector list = new Vector();
	private String sName = "";

	public MyEventSource()
	{
		super();
	}

	public void addMyEventListener(MyEventListener me)
	{
		list.add(me);
	}

	public void deleteMyEventListener(MyEventListener me)
	{
		list.remove(me);
	}

	public void notifyMyEvent(MyEvent me)
	{
		Iterator it = list.iterator();
		while (it.hasNext())
		{
			((MyEventListener) it.next()).handleEvent(me);
		}
	}

	public void setName(String str)
	{
		boolean bool = false;
		if (str == null && sName != null)
			bool = true;
		else if (str != null && sName == null)
			bool = true;
		else if (!sName.equals(str))
			bool = true;
		this.sName = str;
		// 如果改变则执行事件
		if (bool)
			notifyMyEvent(new MyEvent(this, sName));
	}

	public String getsName()
	{
		return sName;
	}

}
