package com.lyw.test;

public class TestBreakAndSwitch
{
	
	private void test() {
		int i=0;
		while(i<100) {
			int res = i%2;
			switch (res)
			{
			case 0:
				System.out.println("i: " + i);
				break;

			case 1:
				System.out.println("haha");
				break;
				
			default:
				break;
			}
			
			i++;
		}
	}
	public static void main(String[] args)
	{
		new TestBreakAndSwitch().test();
	}
	
	
}
