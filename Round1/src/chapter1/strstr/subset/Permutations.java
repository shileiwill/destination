package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.List;

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
