package com.lyw.test;

import com.util.P;

public class Math {
	private void testDiv() {
		System.out.println("1) " + 1 / 2);
		System.out.println("2) " + -1 / (float) 2);
	}

	private void testMod() {
		P.print(0 / 3);
		P.print(0 % 3);
	}

	private void testMod(int which) {
		P.print(which / 3);
		P.print(which % 3);
	}
	
	private void testMod(int which, int anchor) {
		P.print(which / anchor);
		P.print(which % anchor);
	}

	private void testCell() {
		P.print("" + java.lang.Math.ceil(1.00001));
	}

	private void testAbs() {
		P.print(java.lang.Math.abs(-0.1001) + "");
	}

	public static void main(String[] args) {
//		new Math().testCell();
		
		/*{
			Math m = new Math();
			TestShare test = m.new TestShare();
			test.init();
			test.printResult();
		}*/
		
		new Math().testMod(3, 10);
	}
	
	

	class TestShare {
		private final static int total = 100;
		private final static int totalWidth = 370;
		
		private int mBaseStep = 0;
		private int mStepMore = 0;
		private int mTotalMod = 0;
		private int mMoreMod = 0;
		private int mX = 0;

		public void printResult() {
			int width = 0;
			int step = 0;
			for (int i = 1; i <= total; i++) {
				step = mBaseStep;
				boolean flag = mMoreMod == 0;
				if(flag) {
					if(i % mStepMore == 0)
						step++;
				} else {
					if(i % mStepMore == 0 && i % mTotalMod != 0 && i % mX != 0)
						step++;
				}
				width += step;
				System.out.println(i+": step: " + step + "; width: " + width);
			}
		}
		
		public void init() {
			mBaseStep = totalWidth / total;
			mTotalMod = totalWidth % total; // 30
			mStepMore = total / mTotalMod; // 3
			mMoreMod = total % mTotalMod; // 10
			mX = total / mMoreMod;
		}
	}
}
