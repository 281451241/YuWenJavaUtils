package com.util;

public class testView
{
	public int whichBox(int a, int b) {
		int x = (a + getBoxWidth() / 2) / getBoxWidth();
		int y = (b + getBoxHight() /2) / getBoxHight();
		P.print(x + ": " + y);
		P.print(3 * y + x);
		return 0;
	}
	
	
	private int getBoxWidth() {
		return 480 / 3; // 160
	}
	private int getBoxHight() {
		return 800 / 5; // 160
	}
	
	public static void main(String[] args)
	{
		new testView().whichBox(0, 240);
	}
}
