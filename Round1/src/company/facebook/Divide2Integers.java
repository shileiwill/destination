package company.facebook;
/**
 * 29. Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */
public class Divide2Integers {

	public static void main(String[] args) {
		System.out.println(divideMod(265, 12));
	}

	static int divide(int A, int B) {
		int flag = 1;
		
		if ((A > 0 && B < 0) || (A < 0 && B > 0)) {
			flag = -1;
		}
		
		long C = Math.abs((long)A);
		long D = Math.abs((long)B); // C / D;
		
		if (C == 0 || C < D) {
			return 0;
		}
		
		if (D == 0) {
			return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		
		long left = 1;
		long right = C;
		
		while (left + 1 < right) {
			long mid = left + (right - left) / 2;
			
			if (mid <= C / D) {
				if ((mid + 1) > C / D) {
					return (int)mid * flag;
				}
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (right * D <= C) {
			return (int)right;
		} else {
			return (int)left;
		}
	}
	
	static int divideMod(int C, int D) {
		int flag = 1;
		
		if ((C > 0 && D < 0) || (C < 0 && D > 0)) {
			flag = -1;
		}
		
		if (C == 0 || C < D) {
			return C;
		}
		
		if (D == 0) {
			return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		
		int left = 1;
		int right = C;
		
		while (true) {
			int mid = left + (right - left) / 2;
			
			double val = (C * 1.0 / D);
			if (mid == val) {
				return 0;
			} else if (mid < val) {
				if ((mid + 1) > val) { // 也是求商，只不过最终return的时候转一下
					return (C - mid * D) * flag;
				}
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
}
