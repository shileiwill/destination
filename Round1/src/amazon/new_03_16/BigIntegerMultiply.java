package amazon.new_03_16;
/**
 * Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! 
From the following draft, we can immediately conclude:

 `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` 
 */
public class BigIntegerMultiply {

	public static void main(String[] args) {

	}
	
	public int[] multiply(int[] num1, int[] num2) {
		int m = num1.length;
		int n = num2.length;
		
		int[] res = new int[m + n];
		
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int now = num1[i] * num2[j];
				
				int p1 = i + j + 1;
				int p2 = i + j;
				
				now += res[p1];
				res[p2] += now / 10;
				res[p1] = now % 10;
			}
		}
		
		return res;
	}

	int divide2Integers(int val1, int val2) {
		int flag = ((val1 < 0 && val2 > 0) || (val1 > 0 && val2 < 0)) ? -1 : 1;
		
		long num1 = Math.abs(Long.valueOf(val1));
		long num2 = Math.abs(Long.valueOf(val2));
		
		if (num2 == 0) {
			return Integer.MAX_VALUE;
		}
		if (num1 < num2) {
			return 0;
		}
		
		long left = 1, right = num1;
		while (true) {
			long mid = left + (right - left) / 2;
			
			if (mid > num1 / num2) {
				right = mid - 1;
			} else {
				if ((mid + 1) > num1 / num2) {
					if (flag == 1 && mid > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else if (flag == -1 && flag * mid < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
					return (int)(flag * mid);
				}
				left = mid + 1;
			}
		}
		
	}
}
