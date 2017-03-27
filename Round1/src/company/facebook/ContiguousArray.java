package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {

    public int findMaxLengthSlow(int[] nums) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        int max = 0;
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int distance = j - i;
                int value = sum[j] - sum[i];
                
                if (distance % 2 == 0 && value != 0 && distance % value == 0 && distance / value == 2) {
                    max = Math.max(max, distance);
                }
            }
        }
        
        return max;
    }
    
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        
        int max = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // Sum -> Index
        map.put(0, -1);
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }        
        
        return max;
    }

}
