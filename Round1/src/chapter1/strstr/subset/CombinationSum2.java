package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

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
 * @author Lei
 *
 */
public class CombinationSum2 {

	public static void main(String[] args) {
		CombinationSum2 combinationSum = new CombinationSum2();
		int[] nums = {2, 3, 6, 7};
		
		List<List<Integer>> res = combinationSum.combinationSum2(nums, 7);
	}
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (candidates == null) {
            return null;
        }
        
        Arrays.sort(candidates);
        helper(res, list, candidates, target, 0);
        
        return res;
    }
    
    // Must use pos here. If not, [2, 2, 3], [2, 3, 2] will be there. So, make sure subsets are unique.
    // This is combination problem, not permutation, so use position!
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target, int pos) {
        int sum = getSum(list);
        if (target == sum) {
        	res.add(new ArrayList<Integer>(list));
        	return; // Once satisfied, return.
        } else if (target < sum) {
            return; // Too big, stop.
        }
        
        for (int i = pos; i < nums.length; i++) {
            // If current is same with previous, and previous is not selected, then skip
        	// For example, [1, 1, 7]. without this if, [1(1), 7],[1(2), 7] will coexist.
            if (i != pos && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            helper(res, list, nums, target, i + 1); // Keep moving forward, not including itself. One element only once.
            list.remove(list.size() - 1);
        }
    }
    
    private int getSum(List<Integer> m) {
        int sum = 0;
        for(int i = 0; i < m.size(); i++)
        {
            sum = sum + m.get(i);
        }
        return sum;
    }
}
