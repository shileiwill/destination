package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 * @author Lei
 *
 */
public class SubsetsWithDup {

	public static void main(String[] args) {

	}

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        Arrays.sort(nums); // Sort and reconigize duplicate elements easily
        helper(res, list, nums, 0);
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int pos) {
    	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list
        
        for (int i = pos; i < nums.length; i++) { // Dont repeat yourself. Start from a new point always.
            // If 2 elements are same, and if the first element is not selected, then next ones should be ignored.
            if (i != pos && nums[i] == nums[i - 1]) {
            	// i != pos, means we executed for loop at least once, and we removed the previous element(not selected)
                continue;
            }
            list.add(nums[i]);
            helper(res, list, nums, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
    }
}
