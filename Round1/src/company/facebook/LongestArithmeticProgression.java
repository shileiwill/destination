package company.facebook;

import java.util.Arrays;
/*
 * 最长等差数列
 * Given a set of numbers, find the Length of the Longest Arithmetic Progression (LLAP) in it.

Examples:

set[] = {1, 7, 10, 15, 27, 29}
output = 3
The longest arithmetic progression is {1, 15, 29}

set[] = {5, 10, 15, 20, 25, 30}
output = 6
The whole set is in AP
 */
public class LongestArithmeticProgression {

	public static void main(String[] args) {
		int[] arr = {5, 10, 15, 20, 25, 30};
		int max = longestArithmeticProgressionDP(arr);
		int max2 = longestArithmeticProgressionDPBad(arr);
		int max3 = longestArithmeticProgression(arr);
		System.out.println(max);
		System.out.println(max2);
		System.out.println(max3);
	}

	/**
	 * Brute Force. Fix 2 points, and search afterward. O(N^3)
	 * @return
	 */
	static int longestArithmeticProgression(int[] arr) {
		Arrays.sort(arr);
		
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int diff = arr[j] - arr[i];
				int target = arr[j] + diff;
				int count = 2;
				
				for (int k = j + 1; k < arr.length; k++) {
					if (arr[k] == target) {
						count++; // Find one more
						target = target + diff; // Next target
					}
				}
				
				max = Math.max(max, count);
			}
		}
		
		return max;
	}
	
	/**
	 * DP could reduce to O(N^2)
	 * @param arr
	 * @return 
	 * j必须从右边开始扫吗 必须的啊， hash[i][j] = hash[j][k] + 1; 需要使用后边的值
	 */
	static int longestArithmeticProgressionDP(int[] arr) {
		int len = arr.length;
		int[][] hash = new int[len][len];
		int max = 0;
		
		for (int i = 0; i < len - 1; i++) {
			hash[i][len - 1] = 2;
		}
		
		// Consider every element as second/middle element
		for (int j = len - 2; j >= 0; j--) { // 目标是hash[i][j]
			int i = j - 1;
			int k = j + 1;
			
			// i   j   k
			while (i >= 0 && k < len) {
				if (arr[j] - arr[i] > arr[k] - arr[j]) {
					k++; // 继续往右边搜，可能能搜到
				} else if (arr[j] - arr[i] < arr[k] - arr[j]) {
					hash[i][j] = 2; //右边的更大了
					i--;
				} else { // equal
					hash[i][j] = hash[j][k] + 1;
					max = Math.max(max, hash[i][j]);
					i--;
					k++;
				}
			}
			
			// If there is leftover on left side
			while (i >= 0) {
				hash[i][j] = 2;
				i--;
			}
		}
		
		return max;
	}
	
	// 不对啊，必须得从后边扫才行
	static int longestArithmeticProgressionDPBad(int[] arr) {
		int len = arr.length;
		int[][] hash = new int[len][len];
		int max = 0;
		
		for (int i = 0; i < len - 1; i++) {
			hash[i][len - 1] = 2;
		}
		
		// Consider every element as second/middle element
		for (int j = 1; j < len - 1; j++) { // 目标是hash[i][j]
			int i = j - 1;
			int k = j + 1;
			
			// i   j   k
			while (i >= 0 && k < len) {
				if (arr[j] - arr[i] > arr[k] - arr[j]) {
					k++; // 继续往右边搜，可能能搜到
				} else if (arr[j] - arr[i] < arr[k] - arr[j]) {
					hash[i][j] = 2; //右边的更大了
					i--;
				} else { // equal
					hash[i][j] = hash[j][k] + 1;
					max = Math.max(max, hash[i][j]);
					i--;
					k++;
				}
			}
			
			// If there is leftover on left side
			while (i >= 0) {
				hash[i][j] = 2;
				i--;
			}
		}
		
		return max;
	}
}
