package company.expedia;

import java.util.Arrays;

public class ZigZagMaxMin {

	public static void main(String[] args) {
		int[] arr = {3, 5, 1, 9, 7, 4, 7};
		ZigZagMaxMin z = new ZigZagMaxMin();
		
		z.zigZag(arr);
	}

	void zigZag(int[] arr) {
		Arrays.sort(arr);
		
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			if (left == right) {
				System.out.println(arr[left]);
			} else {
				System.out.println(arr[right]);
				System.out.println(arr[left]);
			}
			
			left++;
			right--;
		}
	}
}
