package company.expedia;

import java.util.Arrays;

public class MinMove {

	public static void main(String[] args) {
		MinMove mm = new MinMove();
		int[] arr = {0, 0, 0, 0, 1, 1, 1};
		int count = mm.minMove(arr);
		System.out.println(count);
	}

	int minMove(int[] arr) {
		// Move 0s to left
		int min1 = check(arr, 0, 1);
		// Move 0s to right
		int min2 = check(arr, 1, 0);
		
		return Math.min(min1, min2);
	}
	
	int check(int[] original, int leftVal, int rightVal) {
		int[] copy = Arrays.copyOfRange(original, 0, original.length);
		
		int len = copy.length;
		int count = 0;
		int left = 0, right = 0;
		
		while (right < len) {
			while (left < len && copy[left] != rightVal) { // Find the first right value
				left++;
			}
			
			right = left + 1;
			while (right < len && copy[right] != leftVal) {
				right++;
			}
			
			if (right != len && left != right && copy[left] == rightVal && copy[right] == leftVal) {
				swap(copy, left, right);
				count += (right - left);
			}
			
			left++;
			right++;
		}
		
		return count;
	}
	
	void swap(int[] arr, int left, int right) {
		int tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
	}
}
