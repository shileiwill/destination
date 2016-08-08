package chapter8.frequent1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

 Notice

There is at least one subarray that it's sum equals to zero.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
 * @author Lei
 *
 */
public class SubArraySum {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int[] sum = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum[i] = nums[i];
            } else {
                sum[i] = sum[i - 1] + nums[i];
            }
            if (sum[i] == 0) {
                res.add(0);
                res.add(i);
                return res;
            }
        }
        // When looking for a previous value, map can save a for loop
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(sum[i])) {
                res.add(map.get(sum[i]) + 1);
                res.add(i);
                return res;
            } else {
                map.put(sum[i], i);
            }
        }
        
        return res;
    }
    
    // Using 2 for loops rather than map. length of sum is len + 1
    public ArrayList<Integer> subarraySum2(int[] nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int[] sum = new int[nums.length + 1];
        
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (sum[i] == 0) { // Better to check here
                res.add(0);
                res.add(i - 1);
                return res;
            }
        }
        
        // Go through sum array, 如果俩数相等，说明中间的是sum == 0
        for (int i = 1; i <= nums.length; i++) {
            // if (sum[i] == 0) { // Will be more efficient if checking while building sum array
            //     res.add(0);
            //     res.add(i - 1);
            //     return res;
            // }
            for (int j = i + 1; j <= nums.length; j++) {
                if (sum[i] == sum[j]) {
                    res.add(i);
                    res.add(j - 1);
                    return res;
                }
            }
        }
        
        return res;
    }
}
