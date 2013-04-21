package com.lyw.test;

public class Ab extends Aa{

	@Override
	public void f() {
		// TODO Auto-generated method stub
//		super.f();
		System.out.println("f()");
	}
	
	public void test(){
	       System.out.println(super.getClass().getName());
	    }


	public static void main(String[] args) {
		Aa a = new Ab();
		a.f();
		a.p();
		System.out.println(a.tryCatch());
		new Ab().test();
		//		f();
	}
}
