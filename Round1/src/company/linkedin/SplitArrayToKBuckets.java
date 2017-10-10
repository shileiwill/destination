package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * given a list of numbers, see if you can separate them in to k groups such
 * that each group has the same sum. 给一个数组， 给一个数字 k. 问能不能把数组里面的数字分到k个桶里面，
 * 使得每个桶里面所有的数字和相同。 DFS暴力解。 活学活用DFS backtracking, 寻找各种方案
 * 
 * New: num array问能不能平均放在k个bucket，这应该是NP吧，中间说可能有负数，结果出了俩bug
 */
public class SplitArrayToKBuckets {

	public static void main(String[] args) {
		SplitArrayToKBuckets s = new SplitArrayToKBuckets();
		int[] arr = { 1, 2, 4, 5, 3, 8, 9, 4, 1, 3 };
		int k = 5;
		System.out.println(s.canSplitBruteForce(arr, k));
	}

	// Bruce
	// Time complexity: O((2^n)*k) n is nums.length
	// Space complexity: O(k) for keeping track of visited
	public static boolean separate(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
			return false;
		}
		
		int sum = Arrays.stream(nums).sum();
		if (sum % k != 0) {
			return false;
		}

		int j = 1;
		boolean[] isUsed = new boolean[nums.length];
		
		while (j++ <= k) {
			if (!canSeparateAGroup(isUsed, nums, sum / k, 0)) {
				return false; // K 个group, 只要有一个不满足， 就不行
			}
		}
		return true;
	}

	public static boolean canSeparateAGroup(boolean[] isUsed, int[] nums, int target, int ith) {
		if (target == 0) { // Current group is satisfied
			return true;
		}
		
		if (ith == nums.length) { // Current group is not satisfied, and index goes over the range
			return false;
		}

		// Not using cur item for this group; skip current, go to next. Randomly pick elements, as long as it could form target
		if (canSeparateAGroup(isUsed, nums, target, ith + 1)) {
			return true;
		}
		
		if (isUsed[ith]) { // If current item is used, don't pick again
			return false;
		}

		// include the cur element for current group
		isUsed[ith] = true;
		if (canSeparateAGroup(isUsed, nums, target - nums[ith], ith + 1)) {
			return true;
		}
		isUsed[ith] = false;
		
		return false;
	}

	boolean canSplitBruteForce(int[] arr, int k) {
		int sum = 0;

		for (int val : arr) {
			sum += val;
		}

		if (sum % k != 0) {
			return false;
		}

		int target = sum / k;

		List<Integer> list = new ArrayList<Integer>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		for (int i = 0; i < arr.length; i++) {
			helper(arr, target, k, res, list, 0, i);
		}

		for (List<Integer> list0 : res) {
			for (int val : list0) {
				System.out.print(val + "--");
			}
			System.out.println();
		}
		return flag;
	}

	boolean flag = false;

	private void helper(int[] arr, int target, int k, List<List<Integer>> res, List<Integer> list, int curSum,
			int pos) {
		if (flag) {
			return;
		}

		if (curSum == target) {
			res.add(new ArrayList<>(list));
			if (res.size() == k) { // How to make sure all elements are used?
				flag = true;
			}
			return;
		}

		if (pos == arr.length) {
			return;
		}

		if (res.size() == k) {
			return;
		}

		for (int i = pos; i < arr.length; i++) {
			list.add(arr[i]);
			helper(arr, target, k, res, list, curSum + arr[i], i + 1);
			list.remove(list.size() - 1);
		}
	}
}
