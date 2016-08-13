package chapter8.frequent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 1. Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 * @author Lei
 *
 */
public class TwoSum {
	// Not fully implemented
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        
        int i = 0;
        int j = nums.length - 1;
        // To remember the original indexes, we need to have a ResultType class
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (target == sum) {
                int[] res = {i, j};
                return res;
            } else if (target < sum) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }
    public int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int[] res = {map.get(target - nums[i]), i};
                return res;
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] res = {i, j};
                    return res;
                }
            }
        }
        return null;
    }
}