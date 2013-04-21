package com.lyw.pattern.factory.calculator;

public class Operation {
	public static double getResult(double numA, double numB,
			String operate) {
		double result = 0;
		if("+".equals(operate)) {
			result = numA + numB;
		}
		if("-".equals(operate)) {
			result = numA - numB;
		}
		return result;
	}
	
	private double numA = 0;
	private double numB = 0;
	public double getNumA() {
		return numA;
	}
	public void setNumA(double numA) {
		this.numA = numA;
	}
	public double getNumB() {
		return numB;
	}
	public void setNumB(double numB) {
		this.numB = numB;
	}
	public double getResult() {
		return 0;
	}
}
