package com.lyw.thread;

public class ThreadLocalTest extends Thread {

	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("Thread[" + Thread.currentThread().getName()
					+ "],counter=" + Counter.getNextCounter());
		}
	}
}
