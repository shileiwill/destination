package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarraySumEqualsK {
	// O(n^2)
    public int subarraySum2(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        int count = 0;
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                if (sum[j] - sum[i] == k) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // O(n)
    public int subarraySum(int[] nums, int k) {
        // preSum, count
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        map.put(sum, 1);
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }

}
