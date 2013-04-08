package test;

abstract class Animal {
	public void saySomething() {
		System.out.println("你想说什么就说吧！");
	}
}

class Dog extends Animal {
	public void saySomething() {
		System.out.println("我现在有了第二职业：捉耗");
	}
}

public class Test {
	public static void main(String[] args) {
		Animal ani = new Dog();
		ani.saySomething();
	}
}
