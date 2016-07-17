package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

	public static void main(String[] args) {

	}

    public List<List<Integer>> combine(int n, int k) {
        int[] nums = getArray(n);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (k == 0 || n == 0) {
            return null;
        }
        
        helper(res, list, nums, k, 0);
        
        return res;
    }
    
    // Must use pos here. If not, [2, 2, 3], [2, 3, 2] will be there. So, make sure subsets are unique.
    // This is combination problem, not permutation, so use position!
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int count, int pos) {
        if (list.size() == count) { // The end point is we got enough numbers. 
            res.add(new ArrayList<Integer>(list));
        	return; // Once full, return.
        }
        
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, count, i + 1); // Keep moving forward, not including itself. One element only once.
            list.remove(list.size() - 1);
        }
    }
    
    private int[] getArray(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }
}
