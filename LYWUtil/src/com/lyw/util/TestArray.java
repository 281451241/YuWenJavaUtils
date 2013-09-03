package com.lyw.util;

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
}
