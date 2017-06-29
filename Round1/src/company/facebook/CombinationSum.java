package company.facebook;

import java.util.*;
//重要
public class CombinationSum {

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] candidates = {1, 1, 2, 2, 3, 4};
		int target = 5;
		cs.combinationSum4_5(candidates, target);
	}

	/**
	 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.

	The same repeated number may be chosen from C unlimited number of times.
	
	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [2, 3, 6, 7] and target 7, 
	A solution set is: 
	[
	  [7],
	  [2, 2, 3]
	]
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		helper(res, list, candidates, target, 0);
		return res;
	}
	
	// Each number can be picked multiple times and all numbers are unique
	void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int pos) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "-");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		if (target < 0) {
			return;
		}
		
		for (int i = pos; i < candidates.length; i++) {
			list.add(candidates[i]);
			helper(res, list, candidates, target - candidates[i], i);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 * Given a collection of candidate numbers (C) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.

	Each number in C may only be used once in the combination.
	
	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
	A solution set is: 
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
	 */
	// Each number can be used only once. 
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		Arrays.sort(candidates);
		Set<Integer> visited = new HashSet<Integer>();
		helper2(res, list, candidates, target, 0, visited);
		return res;
	}
	
	void helper2(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int pos, Set<Integer> visited) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "-");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		if (target < 0) {
			return;
		}
		
		for (int i = pos; i < candidates.length; i++) { // 其实不需要这个visited
			if (i != pos && candidates[i] == candidates[i - 1] && !visited.contains(i - 1)) {
				continue;
			}
			list.add(candidates[i]);
			visited.add(i);
			helper2(res, list, candidates, target - candidates[i], i + 1, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
	
	/**
	 * Find all possible combinations of k numbers that add up to a number n, 
	 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
	Example 1:
	
	Input: k = 3, n = 7
	
	Output:
	
	[[1,2,4]]
	
	Example 2:
	
	Input: k = 3, n = 9
	
	Output:
	
	[[1,2,6], [1,3,5], [2,3,4]]
	 */
	List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		helper3(res, list, k, n, 1);
		return res;
	}

	private void helper3(List<List<Integer>> res, List<Integer> list, int k, int n, int pos) {
		if (list.size() == k) {
			if (n == 0) {
				for (int val : list) {
					System.out.print(val + "-");
				}
				System.out.println();
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}
		
		if (n < 0) {
			return;
		}
		
		for (int i = pos; i <= 9; i++) {
			list.add(i);
			helper3(res, list, k, n - i, i + 1);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
	Example:
	
	nums = [1, 2, 3]
	target = 4
	
	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)
	
	Note that different sequences are counted as different combinations.
	
	Therefore the output is 7.
	Follow up:
	What if negative numbers are allowed in the given array?
	How does it change the problem?
	What limitation we need to add to the question to allow negative numbers?
	 */
	public int combinationSum4(int[] nums, int target) {
		int[] hash = new int[target + 1];
		hash[0] = 1;
		
		for (int t = 1; t <= target; t++) {
			hash[t] = 0;
			for (int i = 0; i < nums.length; i++) {
				if (t - nums[i] >= 0) {
					hash[t] = hash[t] + hash[t - nums[i]];
				}
			}
		}
		
		System.out.println(hash[target]);
		return hash[target];
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
	
	// What if negative numbers exist
	public int combinationSum5(int[] nums, int target) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper5(res, list, nums, target);
		
		System.out.println(res.size());
		return res.size();
	}

	// Stack over flow. Doesnt work
	private void helper5(Set<List<Integer>> res, List<Integer> list, int[] nums, int target) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
			helper5(res, list, nums, target - nums[i]);
			list.remove(list.size() - 1);
		}
	}
}
