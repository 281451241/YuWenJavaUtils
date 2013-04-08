package test;

import java.io.Serializable;

public abstract class Aa implements Serializable{
	String strAa = "aa";
	public void f() {
		synchronized (strAa) {
			
		}
		System.out.println(strAa);
	}
	
	public void p() {
		System.out.println("pp");
	}
	
	public void sp() {
		String orgStr = "java, c++, .Net";
		String[] result = orgStr.split(",");
	}
	
	public int tryCatch() {
		int i = 2;
		try {
			return i;
		} finally {
			i = 3;
//			System.out.println("finally" + i);
			return i;
		}
	}
	public static void main(String[] args) {
		System.out.println("aaaaaa");
	}
}
