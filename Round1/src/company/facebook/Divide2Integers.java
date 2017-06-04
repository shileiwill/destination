package company.facebook;
/**
 * 29. Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */
public class Divide2Integers {

	public static void main(String[] args) {
		System.out.println(divide(265, 12));
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
}
