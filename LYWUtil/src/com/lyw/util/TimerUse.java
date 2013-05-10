package com.lyw.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUse
{
	public static void main(String[] args)
	{
		PickTask pt = new PickTask();
		pt.start(1, 3);
	}
}

class PickTask
{
	private Timer timer;

	public PickTask()
	{
		timer = new Timer();
	}

	private TimerTask task = new TimerTask()
	{
		public void run()
		{

			try
			{
				BufferedReader br = new BufferedReader(new FileReader(
						"ming.txt"));
				String data = null;
				while ((data = br.readLine()) != null)
				{
					System.out.println(data);
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("can not find the file");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	};

	public void start(int delay, int internal)
	{
		timer.schedule(task, delay * 1000, internal * 1000);
	}

}
