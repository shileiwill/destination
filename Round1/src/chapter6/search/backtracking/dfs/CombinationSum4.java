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

    Map<Integer, Integer> map = new HashMap<>();

    // 1. helper function to return. Adding Memoization
    public int combinationSum4(int[] nums, int target) {    
        return helper2(nums, target);
    }

    // return is how many solutions for the given input
    int helper2(int[] nums, int remaining) {
        if (remaining == 0) {
            return 1; // pick nothing is one solution
        }
        if (remaining < 0) {
            return 0;
        }

        if (map.containsKey(remaining)) {
            return map.get(remaining);
        }

        int res = 0;
        for (int num : nums) {
            res += helper2(nums, remaining - num);
        }

        map.put(remaining, res);
        return res;
    }

    // 2. No return in helper function, use a global variable
    // Can apply memoization? How?
    public int combinationSum4(int[] nums, int target) {  
        helper(nums, target);
        return numSolutions;
    }

    int numSolutions = 0;
    void helper(int[] nums, int target) {
        if (target == 0) {
            numSolutions += 1;
            return;
        }
        if (target < 0) {
            return; 
        }

        for (int num : nums) {
            helper(nums, target - num);
        }
    }

    // 3. Find all the solutions, and get size. Without Memoization
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();

        helper(res, list, nums, target);
        return res.size();
    }

    void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int num : nums) {
            list.add(num);
            helper(res, list, nums, target - num);
            list.remove(list.size() - 1);
        }
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

}
