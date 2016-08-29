package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.List;
/**
 * 377. Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

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
 * @author Lei
 *
 */
public class CombinationSum4 {

	public static void main(String[] args) {
		CombinationSum4 cs4 = new CombinationSum4();
		int[] nums = {4, 2, 1};
		int target = 32;
		
		int res = cs4.combinationSum4(nums, target);
		System.out.println(res);
	}
	
    // Same with Climbing stairs
    public int combinationSum4(int[] nums, int target) {
        int[] hash = new int[target + 1];
        hash[0] = 1; // Just select nothing, there is 1 solution
        
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    hash[i] = hash[i] + hash[i - num];
                }
            }
        }
        
        return hash[target];
    }
    
    // Backtracking is too slow
    public int combinationSum4Backtracking(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        helper(res, list, nums, target);
        
        return res.size();
    }
    
    void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            for (int val : list) {
            	System.out.print(val + " - ");
            }
            System.out.println();
            return;
        } else if (target < 0) {
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, target - nums[i]);
            list.remove(list.size() - 1);
        }
    }

}
