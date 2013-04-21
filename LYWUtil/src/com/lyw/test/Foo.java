package com.lyw.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Foo {
	int value;
	// TreeSet<ArrayList<String,Integer>> tree=new
	// TreeSet<ArrayList<String,Integer>>();

	List<HashMap<Number, String>> list = new LinkedList<HashMap<Number, String>>();
	static List<Integer> list1 = new ArrayList<Integer>();
	static {
		list1.add(1);
		list1.add(3);
		list1.add(4);
		list1.add(6);
		list1.add(8);
	}
	static List<Integer> list2 = new ArrayList<Integer>();
	static {
		list2.add(2);
		list2.add(5);
		list2.add(9);
	}

	private void sortA() {
		List<Integer> list3 = new ArrayList<Integer>();
		int size = list1.size() + list2.size();
		for (int i = 0, j = 0, k = 0; k < size; k++) {
			if (j >= list2.size() || i > list1.size()
					&& list1.get(i) < list2.get(j)) {
				list3.add(list1.get(i++));
			} else {
				list3.add(list2.get(j++));
			}
		}
		System.out.println(list3);
	}
	
	private void sortB() {
		List<Integer> list3 = new ArrayList<Integer>();
		int size = list1.size() + list2.size();
		for (int i = 0, j = 0, k = 0; k < size; k++) {
			if (j >= list2.size() || i < list1.size()
					&& list1.get(i) < list2.get(j)) {
				list3.add(list1.get(i++));
			} else {
				list3.add(list2.get(j++));
			}
		}
		System.out.println(list3);
	}
	private void sortC() {
		List<Integer> list3 = new ArrayList<Integer>();
		int size = list1.size() + list2.size();
		for (int i = 0, j = 0, k = 0; k < size; k++) {
			if (j >= list2.size() || i < list1.size()
					&& list1.get(i) > list2.get(j)) {
				list3.add(list1.get(i++));
			} else {
				list3.add(list2.get(j++));
			}
		}
		System.out.println(list3);
	}
	
	private void sortD() {
		List<Integer> list3 = new ArrayList<Integer>();
		int size = list1.size() + list2.size();
		for (int i = 0, j = 0, k = 0; k < size; k++) {
			if (j >= list2.size() || i < list1.size()
					|| list1.get(i) < list2.get(j)) {
				list3.add(list1.get(i++));
			} else {
				list3.add(list2.get(j++));
			}
		}
		System.out.println(list3);
	}

	Foo(int value) {
		this.value = value;
	}

	static boolean loop(char c) {
		System.out.print(c);
		return true;
	}

	static void testTryCatch() {
		try {
			int x = 0;
			float y = 0;
			float z = x / y;
			System.out.println("11");
			try {
				int a[] = new int[3];
				a[3] = x;
				System.out.println("22");
				return;
			} catch (Exception e) {
				System.out.println("33");
				return;
			} finally {
				System.out.println("44");
				// System.exit(0);
				return;
			}
		} catch (ArithmeticException e) {
			System.out.println("55");
			return;
		} finally {
			System.out.println("66");
			return;
		}
	}

	public static void main(String[] args) {
		Foo f = new Foo(0);
		f.sortA();
		// int i = 0;
		// for (loop('A'); loop('B') && (i < 2); loop('C')) {
		// i++;
		// loop('D');
		// }
//		testTryCatch();
	}

	// public static void main(String[] args) {
	// Foo[] a1 = { new Foo(1), new Foo(2), new Foo(3) };
	// Foo[] a2 = new Foo[a1.length];
	// System.arraycopy(a1, 0, a2, 0, a1.length);
	// System.out.println((a1 == a2) + "," + (a1[1] == a2[1]));
	// }
}
