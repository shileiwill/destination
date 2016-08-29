package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 216. Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
 * @author Lei
 *
 */
public class CombinationSum3 {

	public static void main(String[] args) {
		CombinationSum3 combinationSum = new CombinationSum3();
		int[] nums = {2, 3, 6, 7};
		
	}
	
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (k == 0 || n == 0) {
            return null;
        }
        
        helper(res, list, nums, n, k, 0);
        
        return res;
    }
    
    // Must use pos here. If not, [2, 2, 3], [2, 3, 2] will be there. So, make sure subsets are unique.
    // This is combination problem, not permutation, so use position!
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target, int count, int pos) {
        if (list.size() == count) { // The end point is we got enough numbers. 
        	int sum = getSum(list);
            if (target == sum) {
            	res.add(new ArrayList<Integer>(list));
            }
        	return; // Once full, return.
        }
        
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, target, count, i + 1); // Keep moving forward, not including itself. One element only once.
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
