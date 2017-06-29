package company.linkedin;

import java.util.Arrays;

public class MaxProductOfTriplet {

	public static void main(String[] args) {
		int[] arr = {1, -4, 3, 6, 7, 0};
		
		MaxProductOfTriplet mp = new MaxProductOfTriplet();
		System.out.println(mp.maxProduct(arr));
	}

	// O(Nlog(N))
	int maxProduct(int[] arr) {
		Arrays.sort(arr);
		
		int val1 = arr[0] * arr[1] * arr[arr.length - 1];
		int val2 = arr[arr.length - 3] * arr[arr.length - 2] * arr[arr.length - 1];
		
		return Math.max(val1, val2);
	}
	
	// O(N)
	int maxProduct2(int[] arr) {
		int min1 = Integer.MAX_VALUE; // smallest
		int min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE; // largest
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		
		for (int val : arr) {
			if (val <= min1) {
				min2 = min1;
				min1 = val;
			} else if (val < min2) {
				min2 = val;
			}
			
			if (val >= max1) {
				max3 = max2;
				max2 = max1;
				max1 = val;
			} else if (val >= max2) {
				max3 = max2;
				max2 = val;
			} else if (val > max3) {
				max3 = val;
			}
		}
		
		int res1 = min1 * min2 * max1;
		int res2 = max1 * max2 * max3;
		
		return Math.max(res1, res2);
	}
}
