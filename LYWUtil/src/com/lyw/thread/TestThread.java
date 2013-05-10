package com.lyw.thread;

public class TestThread {
	int count = 0;

	private void firstThread() {
		new Thread() {
			public void run() {
				try {
					while (count < 11) {
						print();
						count++;
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void print() {
		System.out.println(count);
	}

	private void t() {
		int x = 0;
		int y = 1;
		int a = x++ + ++x; // 0+2
		int b = y-- - --y; // 1 - -1
		System.out.println(++a + b++); // 3+2
	}

	private void t1() {
		int total = 0;
		for (int i = 0, j = 5; total < 10 || j > 3; ++i, --j) {
			total += (i + j);
			System.out.println(total);
		}
	}
	
	private void testJion() {
		try
		{
			Thread td = new Thread() {
				public void run() {
					for(int i=0; i<20; i++) {
						System.out.println("i: " + i);
						try
						{
							Thread.sleep(100);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			};
			td.start();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testSleep() {
		System.out.println("Thread start!");
		new Thread(){
			@Override
			public void run()
			{
				try
				{
					System.out.println("sleep start!");
					sleep(3000);
					System.out.println("sleep ok!");
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		System.out.println("Thread ok!");
	}

	public static void main(String[] args) {
		TestThread tt = new TestThread();
//		tt.firstThread();
//		tt.t();
		
		tt.testJion();
	}
}
