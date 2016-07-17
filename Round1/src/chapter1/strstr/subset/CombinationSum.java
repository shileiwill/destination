package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		CombinationSum combinationSum = new CombinationSum();
		int[] nums = {2, 3, 6, 7};
		
		List<List<Integer>> res = combinationSum.combinationSum(nums, 7);
	}
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (candidates == null) {
            return null;
        }
        
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
            list.add(nums[i]);
            helper(res, list, nums, target, i); // Keep moving forward, including itself. One element can repeat unlimited times.
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
