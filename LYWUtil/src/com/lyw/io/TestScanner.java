package com.lyw.io;

public class TestScanner
{
	public static void main(String[] args)
	{
		long cur = System.currentTimeMillis();

		new KuAnLongScanner().listAllFiles();

		System.out.println("time: " + (System.currentTimeMillis() - cur));

		cur = System.currentTimeMillis();
		new FileTreeTest().scan();
		System.out.println("time: " + (System.currentTimeMillis() - cur));

	}
}
