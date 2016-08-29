package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 * @author Lei
 *
 */
public class PermutationsWithDup {

	public static void main(String[] args) {
		PermutationsWithDup permutations = new PermutationsWithDup();
		int[] nums = {2, 1, 2, 3};
		List<List<Integer>> res = permutations.permute(nums);
	}

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        helper(res, list, nums, visited);
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) { // Dont need position any more, will always from the beginning.
        if (list.size() == nums.length) {
        	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list
        	return; // Once got the full list, one round is done.
        }
        
        for (int i = 0; i < nums.length; i++) { // Always start from 0. Position matters.
        	// Already visited || Same with previous one, and the previous one is not selected, then skip
        	if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
        		// Why create visited array? We cant use list.contains any longer, as there are duplicates.
        		continue;
        	}
        	visited[i] = true;
        	list.add(nums[i]);
            helper(res, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
