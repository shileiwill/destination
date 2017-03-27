package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinousSubarraySum {
	// One Pass
    public boolean checkSubarraySum(int[] nums, int k) {
        // Map的key是共同的余数，只要余数相同，做差之后 就肯定是k的倍数
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        map.put(0, -1);
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            
            if (k != 0) {
                runningSum = runningSum % k;
            }
            
            if (map.containsKey(runningSum)) {
                if (i - map.get(runningSum) >= 2) {
                    return true;
                }
            } else {
                map.put(runningSum, i);
            }
        }
        
        return false;
    }
    
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (j - i < 2) {
                    continue;
                }
                
                int value = sum[j] - sum[i];
                
                if (k == 0) {
                    if (value == 0) {
                        return true;
                    }
                } else if (value % k == 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
