package pattern.factory.calculator;

import java.util.Scanner;

public class SimpleCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入第一个数字");
		double a = sc.nextDouble();
		System.out.println("请输入第二个数字");
		double b = sc.nextDouble();
		System.out.println("请输入运算符");
		char c = sc.next().charAt(0);
//		double result = Operation.getResult(a, b, c);
		Operation oper = OperationFactory.createOperation(c);
		oper.setNumA(a);
		oper.setNumB(b);
		double result = oper.getResult();
		System.out.println("结果为：" + result);
	}
	
}
