package com.lyw.guobi.util;

import java.util.ArrayList;
import java.util.Arrays;

public class t {

	int[] src = {113, 119, 101, 114, 116, 121, 117, 105, 111, 112};
	public t() {
	}
	
	public static void main(String[] args) {
		new t().getCorrectionMap();
	}

	private char[] getCorrectionMap() {

		char[] correctionsArray = null;

		if (correctionsArray == null) {

			ArrayList<String> corrections = new ArrayList<String>();

			int preRow = -1;
			int thisIndex = 0;

			for(int i=0; i<src.length; i++) {
				
				int code = src[i];

				// 处理容错按键
				String preString = thisIndex >= 2 ? corrections
						.get(thisIndex - 2) : String.valueOf((char) 0);
				String thisString = String.valueOf((char) code)
						+ (i != 0 ? String.valueOf(preString
								.charAt(0)) : "");
				preString = preString
						+ (i != 0 ? String.valueOf((char) code)
								: String.valueOf((char) 0));

				System.out.println("preString: " + preString + ":" + thisString
						+ " thisIndex: " + thisIndex);
				if (thisIndex >= 2) {
					corrections.set(thisIndex - 2, preString);
				}
				corrections.add(thisString);
				thisIndex++;
				if (thisIndex >= 2) {
					corrections.set(thisIndex - 2, preString.toUpperCase());
				}
				corrections.add(thisString.toUpperCase());
				thisIndex++;
			}

			int col = 3;// + maxChar;
			correctionsArray = new char[corrections.size() * col];

			int corrIndex = 0;
			for (String corr : corrections) {
				System.out.println("corr: " + corr);
				char[] acorr = corr.toCharArray();
				System.arraycopy(acorr, 0, correctionsArray, corrIndex,
						acorr.length);
				Arrays.fill(correctionsArray, corrIndex + acorr.length,
						corrIndex + col, '\0');
				corrIndex += col;
			}
		}

		return correctionsArray;
	}
}
