package leetcode8.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    // https://discuss.leetcode.com/topic/49741/easy-understood-java-dp-solution-in-28ms-with-o-n-2-time
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        
        int[] hash = new int[nums.length];
        hash[0] = 1; // Itself. To provide convenience to latter elements
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) { // Cache the count
                    hash[i] = Math.max(hash[i], hash[j] + 1);
                }
            }
        }
        
        // Find the max
        int maxIndex = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (hash[i] > hash[maxIndex]) {
                maxIndex = i;
            }
        }
        
        int curValue = nums[maxIndex];
        int curCount = hash[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (curValue % nums[i] == 0 && curCount == hash[i]) {
                res.add(nums[i]);
                curValue = nums[i];
                curCount--;
            }
        }
        
        return res;
    }
}
