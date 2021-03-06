package com.lyw.robot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class LywRobot
{
	public LywRobot()
	{
		try
		{
			mRobot = new Robot();
		}
		catch (AWTException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int screenWidth;
	private int screenHeight;
	private String imageFormat = "jpg"; // 图像文件的格式

	public void setScreenWidth(int screenWidth)
	{
		this.screenWidth = screenWidth;
	}

	public void setScreenHeight(int screenHeight)
	{
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth()
	{
		setScreenWidth((int) java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().width);
		return screenWidth;
	}

	public int getScreenHeight()
	{
		setScreenHeight((int) java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		return screenHeight;
	}

	public void leftKeyOnClick()
	{
		mRobot.mousePress(InputEvent.BUTTON1_MASK);
		mRobot.delay(10);
		mRobot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void rightKeyOnClick()
	{
		mRobot.mousePress(InputEvent.BUTTON3_MASK);
		mRobot.delay(10);
		mRobot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	public void moveToDes(int desX, int desY)
	{
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		int stepSize = (int) (Math.random() * 10);
		int curX = mousepoint.x;
		int curY = mousepoint.y;
		int stepX = (desX - curX) / stepSize;
		int stepY = (desY - curY) / stepSize;
		for (int i = 0; i < stepSize; i++)
		{
			curX += stepX;
			curY += stepY;
			mRobot.mouseMove(curX, curY);
			mRobot.delay(new Random().nextInt(50));
		}
	}

	public void screenShot(int x, int y, int w, int h, String tarName)
	{
		try
		{
			BufferedImage mBufferedImage = mRobot
					.createScreenCapture(new Rectangle(x, y, w, h));
			File temp = new File(tarName);
			ImageIO.write(mBufferedImage, imageFormat, temp);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private Robot mRobot;
}
