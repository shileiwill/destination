package company.linkedin;

import java.util.Arrays;

public class MaxProductOfTriplet {

	public static void main(String[] args) {
		int[] arr = {1, -4, 3, 6, 7, 0};
		
		MaxProductOfTriplet mp = new MaxProductOfTriplet();
		System.out.println(mp.maxProduct(arr));
	}

	int maxProduct(int[] arr) {
		Arrays.sort(arr);
		
		int val1 = arr[0] * arr[1] * arr[arr.length - 1];
		int val2 = arr[arr.length - 3] * arr[arr.length - 2] * arr[arr.length - 1];
		
		return Math.max(val1, val2);
	}
}
