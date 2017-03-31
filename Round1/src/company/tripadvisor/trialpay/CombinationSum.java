package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		int[] candidates = {1, 23, 4, 3, 2, 5, 9, 8};
		int target = 7;
		
		CombinationSum cs = new CombinationSum();
		cs.combinationSum(candidates, target);
		System.out.println("======================================");
		cs.combinationSum2(candidates, target);
		System.out.println("======================================");
		cs.combinationSum3(3, 9);
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		dfs3(k, n, res, list, 1);
		return res;
	}
	
	void dfs3(int k, int n, List<List<Integer>> res, List<Integer> list, int pos) {
		if (list.size() == k) {
			if (n == 0) {
				for (int val : list) {
					System.out.print(val + "--");
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
			dfs3(k, n - i, res, list, i + 1);
			list.remove(list.size() - 1);
		}
	}
	
	public List<List<Integer>> combinationSum2(int[] can, int target) {
		Arrays.sort(can);
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		dfs2(can, target, res, list, 0);
		return res;
	}
	
	void dfs2(int[] can, int target, List<List<Integer>> res, List<Integer> list, int pos) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		if (target < 0) {
			return;
		}
		
		for (int i = pos; i < can.length; i++) {
			if (i != pos && can[i] == can[i - 1]) {
				continue;
			}
			list.add(can[i]);
			dfs2(can, target - can[i], res, list, i + 1);
			list.remove(list.size() - 1);
		}
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		dfs(candidates, target, res, list, 0);
		return res;
	}
	
	void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int pos) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "--");
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
			dfs(candidates, target - candidates[i], res, list, i);
			list.remove(list.size() - 1);
		}
	}
}
