package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 * @author Lei
 *
 */
public class Permutations {

	public static void main(String[] args) {
		Permutations permutations = new Permutations();
		int[] nums = {1, 2, 3};
		
		List<List<Integer>> res = permutations.permute(nums);
	}

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        helper(res, list, nums);
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums) { // Dont need position any more, will always from the beginning.
        if (list.size() == nums.length) {
        	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list
        	return; // Once got the full list, one round is done.
        }
        
        for (int i = 0; i < nums.length; i++) { // Always start from 0. Position matters.
        	if (list.contains(nums[i])) {
        		continue;
        	}
        	list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }
}
