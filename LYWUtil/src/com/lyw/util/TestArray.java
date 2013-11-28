package com.lyw.util;

import java.util.ArrayList;

public class TestArray
{
	private void isArrayItem() {
		String[] abc = new String[]{"a", "b", "c"};
	}
	
	public String[] parseArray(String arrayString, String spilt) {
		
		return arrayString.split(spilt);
	}
	
	/**
	 * 数组转换: 获取二维数组中,第idx列的字符
	 * @param arrayString
	 * @param idx
	 * @return
	 */
	public String[] tranArray(String[][] arrayString, int idx) {
		int n=arrayString.length;
		String[] res = new String[n];
		for(int i=0; i<n; i++) {
			res[i] = arrayString[i][idx];
		}
		return res;
	}
	
	/**
	 * 数组转换: 获取二维数组中,第idx列的对象
	 * @param arrayString
	 * @param idx
	 * @return
	 */
	public Object[] tranArray(Object[][] arrayString, int idx) {
		int n=arrayString.length;
		Object[] res = new Object[n];
		for(int i=0; i<n; i++) {
			res[i] = arrayString[i][idx];
		}
		return res;
	}
	
	public void testCopyArray() {
		int[] src = {0,1,2,3,4,5};
		int[] dsc = new int[7];
		System.arraycopy(src, 2, dsc, 3, 3);
		
		for(int i : dsc) 
			System.out.print(i + ", ");
	}
	
	public void testSetArray() {
		int[] src = {0,1,2,3,4,5};
		ArrayList<String> testArray = new ArrayList<String>();
//		Array.
		for(int i : src) 
			System.out.print(i + ", ");
	}
	
	public static void main(String[] args) {
		new TestArray().testCopyArray();
	}
}
