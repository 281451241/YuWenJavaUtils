package com.lyw.reflect;

public class ReflectTarget {
	private String mMessage = "";
	
	private final static String TAG = "ReflectTarget";
	public ReflectTarget() {
		mMessage = "no params Construction";
	}
	
	public ReflectTarget(String msg) {
		mMessage = msg;
	}
	
	@Override
	public String toString() {
		return TAG + " " + mMessage;
	}
}
