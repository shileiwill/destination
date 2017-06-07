package company.amazon;

import java.util.Arrays;

/**
 * This is a very good question
 * LC上一道题的变体，说把一个array分成两个subsequence（不是subarray），让这两个subsequence的和最接近，返回这两个subsequence。 
 */
public class PartitionSubsetSum {

	public static void main(String[] args) {
		PartitionSubsetSum sub = new PartitionSubsetSum();
		
		int[] N = {3, 5, 2, 1, 6, 6};
		int res = sub.closestSubsetSum(N);
		System.out.println(res);
	}
	
	// Transform, then apply Binary Search
	int closestSubsetSum(int[] N) {
		Arrays.sort(N);
		
		int[] sum = new int[N.length];
		sum[0] = N[0];
		for (int i = 1; i < N.length; i++) {
			sum[i] = sum[i - 1] + N[i];
		}
		
		int target = sum[N.length - 1] / 2;
		
		int left = 0, right = N.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (sum[mid] == target) {
				return (sum[N.length - 1] % 2) == 0 ? 0 : 1;
			}
			
			if (sum[mid] > target) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (Math.abs(sum[left] - target) <= Math.abs(sum[right] - target)) {
			return Math.abs(sum[left] - target);
		}
		
		return Math.abs(sum[right] - target);
	}
	
	// 416 0-1 backpack
	/**
	 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
		Note:
		Each of the array element will not exceed 100.
		The array size will not exceed 200.
		Example 1:
		
		Input: [1, 5, 11, 5]
		
		Output: true
		
		Explanation: The array can be partitioned as [1, 5, 5] and [11].
		Example 2:
		
		Input: [1, 2, 3, 5]
		
		Output: false
		
		Explanation: The array cannot be partitioned into equal sum subsets.
	 */
	public boolean canPartition(int[] nums) {
		int sum = 0;
		int len = nums.length;
		
		for (int num : nums) {
			sum += num;
		}
		
		if (sum % 2 == 1) {
			return false;
		}
		
		boolean[][] hash = new boolean[len + 1][sum + 1];
		hash[0][0] = true;
		
		for (int i = 1; i <= len; i++) {
			hash[i][0] = true;
		}
		
		for (int i = 1; i <= sum; i++) {
			hash[0][i] = false;
		}
		
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= sum; j++) {
				hash[i][j] = hash[i - 1][j];
				
				if (j - nums[i - 1] >= 0) {
					hash[i][j] = hash[i][j] || hash[i - 1][j - nums[i - 1]]; // Must be i - 1, since items cant be used repeatedly
				}
			}
		}
		
		return hash[len][sum];
	}
}
