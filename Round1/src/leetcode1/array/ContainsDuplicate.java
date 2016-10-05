package leetcode1.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 217. Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> cache = new HashSet<Integer>();
        
        for (int num : nums) {
            if (cache.contains(num)) {
                return true;
            }
            cache.add(num);
        }
        
        return false;
    }
    
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        
        return false;
    }
}
