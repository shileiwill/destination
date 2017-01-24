package leetcode15.bfs.dfs.unionfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences {


    public List<List<Integer>> findSubsequences1(int[] nums) {
        Set<List<Integer>> set = new HashSet<List<Integer>>(); // See the main method below
        List<Integer> list = new ArrayList<Integer>();
        helper1(set, list, nums, 0);
        List<List<Integer>> res = new ArrayList<List<Integer>>(set);
        return res;
    }
    
    void helper1(Set<List<Integer>> set, List<Integer> list, int[] nums, int i) {
        if (list.size() >= 2) {
            set.add(new ArrayList<Integer>(list)); // Dont stop from here, continue
        }
        for (int j = i; j < nums.length; j++) {
            if (list.size() == 0 || list.get(list.size() - 1) <= nums[j]) {
                list.add(nums[j]);
                helper1(set, list, nums, j + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        helper(res, list, nums, 0);
        return res;
    }
    
    void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int i) {
        if (list.size() >= 2) {
            res.add(new ArrayList<Integer>(list)); // Dont stop from here, continue
        }
        
        // In this recursion. The equal numbers can only be neighbours
        List<Integer> unique = new ArrayList<Integer>();
        
        for (int j = i; j < nums.length; j++) {
            if (list.size() == 0 || list.get(list.size() - 1) <= nums[j]) {
                if (!unique.contains(nums[j])) {
                    list.add(nums[j]);
                    unique.add(nums[j]);
                    helper(res, list, nums, j + 1);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

	public static void main(String[] args) {
		Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        
        list1.add(1);
        list1.add(2);
        list1.add(3);
        
        list2.add(1);
        list2.add(2);
        list2.add(3);
        
        set.add(list1);
        set.add(list2);
        
        System.out.println(set.size()); // the size will be 1 only
	}

}
