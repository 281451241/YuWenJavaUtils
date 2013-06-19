package com.lyw.test;

import com.util.P;

public class Math
{
	private void testDiv() {
		System.out.println("1) " + 1/2);
		System.out.println("2) " + -1/(float)2);
	}
	
	private void testMod()
	{
		P.print(0/3);
		P.print(0%3);
	}
	private void testMod(int which)
	{
		P.print(which/3);
		P.print(which%3);
	}
	
	private void testCell() {
		P.print("" + java.lang.Math.ceil(1.00001));
	}
	private void testAbs() {
		P.print(java.lang.Math.abs(-0.1001)+ "");
	}
	
	public static void main(String[] args)
	{
		new Math().testCell();
	}
}
