package test;

public abstract class TestInterface implements c {

	@Override
	public void a1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void a2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void c1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void c2() {
		// TODO Auto-generated method stub
		
	}



}
class A1 {
public A1() {
}
public void A1() {
}
public void A1(int x) {
}
}
class B1 extends A1 {
//填入一个方法
	public void A1() {
		
	}
//	public int A1(int x) { 
//	
//	}
}
interface A {
	public void a1();
	public void a2();
}
interface B {
	public void a1();
	public void a2();
}
abstract interface c extends A, B{
	public void c1();
	public void c2();
}
