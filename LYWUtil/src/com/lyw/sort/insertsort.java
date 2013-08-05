package com.lyw.sort;

import java.util.Random;

public class insertsort {
	public static void main(String[] args) {
		long cur = System.currentTimeMillis();
		test t = new test();
		t.init();
		int[] array = t.getA();
		System.out.print("before sort ");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		t.sort();
		System.out.print("\nafter sort ");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");

		
		System.out.println("time: " + (System.currentTimeMillis() - cur));
	}
}

class test {
	private int[] a = new int[100];
	private Random random = null;

	public int[] getA() {
		return a;
	}

	public void init() {
		random = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(99) + 1;
		}
	}

	public void sort() {
		if (a != null) {
			for (int i = 1; i < a.length; i++) {
				int temp = a[i], j = i;
				if (a[j - 1] > temp) {
					while (j >= 1 && a[j - 1] > temp) {
						a[j] = a[j - 1];
						j--;
					}
				}
				a[j] = temp;
			}
		} else
			System.out.print("array hasn't bean inited!");
	}
}