package com.lyw.robot;


import org.junit.Test;

import junit.framework.TestCase;

public class LywRobotTest extends TestCase
{

	@Test
	public final void test()
	{
		LywRobot lr = new LywRobot();
		
		lr.moveToDes(400, lr.getScreenHeight() - 10);
		lr.rightKeyOnClick();
	}

}
