package company.uber;

import java.util.*;

public class CombinationSum {

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] candidates = {1, 2, 3};
		int target = 4;
		cs.combinationSum5(candidates, target);
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		helper(res, list, candidates, target, 0);
		return res;
	}
	
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
	
	public int combinationSum4(int[] nums, int target) {
		int[] hash = new int[target + 1];
		hash[0] = 1;
		
		for (int t = 1; t <= target; t++) {
			hash[t] = 0;
			for (int i = 0; i < nums.length; i++) {
				if (t - nums[i] >= 0) {
					hash[t] += hash[t - nums[i]];
				}
			}
		}
		
		System.out.println(hash[target]);
		return hash[target];
	}
	
	// What if negative numbers exist
	public int combinationSum5(int[] nums, int target) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper5(res, list, nums, target);
		
		System.out.println(res.size());
		return res.size();
	}

	// Stack over flow
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
