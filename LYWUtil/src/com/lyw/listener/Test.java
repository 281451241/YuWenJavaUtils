package com.lyw.listener;

public class Test implements MyEventListener
{
	public Test()
	{
		MyEventSource mes = new MyEventSource();
		mes.addMyEventListener(this);
		mes.setName("niu");
	}

	public static void main(String args[])
	{
		new Test();
	}

	public void handleEvent(MyEvent me)
	{
		System.out.println(me.getSource());
		System.out.println(me.getsName());
	}
}
