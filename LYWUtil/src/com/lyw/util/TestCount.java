package com.lyw.util;

public class TestCount {
	private void sub() {
		for(int i = 0; i < 10; i++) {
			count--;
		}
		
		System.out.println(count);
	}
	
	private void add() {
		for(int i = 0; i < 10; i++) {
			count++;
		}
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		TestCount tc = new TestCount();
		tc.add();
		tc.sub();
	}
	
	private int count = 0;
	
}
