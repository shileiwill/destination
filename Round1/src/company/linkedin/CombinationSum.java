package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Given a target and an array, return the number of combinations from the array that can add to the given number 写了之后他说array里可以有负数，
于是稍微改了一下就好
 */
public class CombinationSum {

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] arr = {1, 1, 2, 3, 4};
		int target = 5;
		System.out.println(cs.combinationSum4_5(arr, target));
//		cs.combinationSum1_5(arr, target);
	}

	// 这个DP 只能 1. 数组不包含重复元素，2. 每个元素可以重复取
	int dp(int[] arr, int target) { // 数组中必须不含重复元素，有重复元素就meaningless了
		int[] hash = new int[target + 1];
		hash[0] = 1;
		
		for (int i = 1; i <= target; i++) {
			for (int j = 0; j < arr.length; j++) { // 每次都遍历所有的元素，必然重复取
				if (i - arr[j] >= 0) {
					hash[i] += hash[i - arr[j]];
				}
			}
		}
		
		return hash[target]; // This includes 1, 1, 1, 1, 1, 1. 同一个元素会重复取
	}
	
	// 每个元素只能用一次，求装满书包的可能性. 这个可以有重复元素
	public int combinationSum4_5(int[] nums, int target) {
		int[][] hash = new int[nums.length + 1][target + 1];
		hash[0][0] = 1;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < hash.length; i++) {
			hash[i][0] = 1;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < hash[0].length; i++) {
			hash[0][i] = 0;
		}
		
		for (int i = 1; i <= nums.length; i++) {
			for (int t = 1; t <= target; t++) {
				hash[i][t] = hash[i - 1][t];
				if (t - nums[i - 1] >= 0) {
					hash[i][t] += hash[i - 1][t - nums[i - 1]]; // 又多一种可能
				}
			}
		}
		
		System.out.println(hash[nums.length][target]);
		return hash[nums.length][target];
	}
	
	int res = 0;
	int combination(int[] arr, int target) {
		Arrays.sort(arr);
		helper(arr, target, 0);
		return res;
	}
	
	void helper(int[] arr, int target, int pos) {
		if (pos == arr.length) {
			return;
		}
		
		if (target == 0 && pos != 0) {
			res++;
//			return;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			helper(arr, target - arr[i], i + 1); // 肯定得是i + 1, 不能重复用当前的元素
		}
	}
	
	// Can use Map to memorize. 数组是排好序的，以一个position为起点的target sum是固定个数的
	int helper(int[] arr, int target, int pos, Map<Integer, Map<Integer, Integer>> map) {
		if (map.containsKey(target) && map.get(target).containsKey(pos)) {
			return map.get(target).get(pos);
		}
		
		int count = 0;
		if (!map.containsKey(target)) {
			map.put(target, new HashMap<>());
		}
		
		if (target == 0 && pos != 0) {
			count++;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			count += helper(arr, target - arr[i], i + 1, map);
		}
		
		map.get(target).put(pos, count);
		return count;
	}
	
	// Regular Combination Sum, 1, 2. Array is sorted. There are duplicates in Array and elements can be used repeatedly
	List<List<Integer>> combinationSum1_5(int[] arr, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		dfs(res, list, arr, target, 0);
		return res;
	}

	// TC 是m^n把如果数组有m个，target是n, O(n)＝m×O(n-1)＝m^(n-1)O(1)＝m^n
	// 11 to the power of 9, M to the power of N
	private void dfs(List<List<Integer>> res, List<Integer> list, int[] arr, int target, int pos) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		if (pos == arr.length) {
			return;
		}
		// 当然，target是正数，并且数组中的元素也都是正数
		if (target < 0) { // This guy must be there, if not, stack over flow. The reason is one element can be picked repeatedly
			return;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) { // This guy must be there, as there are duplicates in sorted array, 1, 1, 1,
				continue;
			}
			list.add(arr[i]);
			dfs(res, list, arr, target - arr[i], i);
			list.remove(list.size() - 1);
		}
	}
}
