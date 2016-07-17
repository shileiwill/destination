package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		int[] nums = {1, 2, 3};
		
		List<List<Integer>> res = subsets.subsets(nums);
		
	}

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        helper(res, list, nums, 0);
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int pos) {
    	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list
        
        for (int i = pos; i < nums.length; i++) { // Dont repeat yourself. Start from a new point always.
            list.add(nums[i]);
            helper(res, list, nums, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
    }
}
