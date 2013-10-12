package com.lyw.thread;

public class Test {

	public static void main(String[] args) throws Exception {

		ThreadLocalTest testThread1 = new ThreadLocalTest();
		ThreadLocalTest testThread2 = new ThreadLocalTest();
		ThreadLocalTest testThread3 = new ThreadLocalTest();

		testThread1.start();
		testThread2.start();
		testThread3.start();

	}
}