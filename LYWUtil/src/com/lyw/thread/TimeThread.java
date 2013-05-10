package com.lyw.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

class Time implements ActionListener
{
	Threads t;
	Timer time;

	public Time(Threads t)
	{
		this.t = t;
		time = new Timer(2000, this);
		time.setRepeats(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		time.stop();
	}
}

class Threads extends Thread
{
	Time temp;
	int i = 0;

	public Threads()
	{
		temp = new Time(this);
	}

	@Override
	public void run()
	{
		temp.time.start();
		long cur = System.currentTimeMillis();
		while (true)
		{
			if (!temp.time.isRunning())
			{
				System.out.println("End");
				break;
			}
		}
		System.out.println(System.currentTimeMillis() - cur);
	}
}

class TestThreads extends Thread {
	
	Timer timer;  //定时器
	
	public TestThreads()
	{
		timer = new Timer(5000, null);	//定时5秒
		timer.setRepeats(false);
	}
	
	@Override
	public void run()
	{
		timer.start();
		long cur = System.currentTimeMillis();
		while (true)
		{
			if (!timer.isRunning())
			{
				System.out.println("End");
				break;
			}
		}
		System.out.println(System.currentTimeMillis() - cur);
	}
}

public class TimeThread
{
	public static void main(String args[])
	{
		TestThreads t = new TestThreads();
		t.start();
	}
}
