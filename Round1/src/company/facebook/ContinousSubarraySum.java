package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Given a list of non-negative numbers and a target integer k, write a function to check 
 * if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

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

is there subarray summed to a target. 像523，但是值可正可负，我用了map, follow-up问全部non-negative怎么办，应该是用two pointer就好了.

刚考了
 */
public class ContinousSubarraySum {
    // First start with subarray target. You are given an array, find a subarray which sums to target
    boolean checkSubarraySum(int[] nums, int target) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int value = sum[j] - sum[i];
                
                if (value - target == 0) {
                    System.out.println(i + ":" + j);
                    return true;
                }
            }
        }
        
        return false;
    }

    // Second, use a map to avoid double for loops
    boolean subarraySumToTarget2(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // Since we dont care about index, Set<Integer> will also work
        map.put(0, -1);
        
        int runningSum = 0;
        for (int i = 0; i < arr.length; i++) {
            runningSum += arr[i];
            
            int toFind = runningSum - target;
            if (map.containsKey(toFind)) {
                return true;
            }
            
            map.put(runningSum, i);
        }
        
        return false;
    }

	// Solution 1: use sum array, this will timeout
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i <= len; i++) {
            for (int j = i + 2; j <= len; j++) {
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

    // Solution 2: This is not easy to get. One Pass, use map and use remainder
    public boolean checkSubarraySum(int[] nums, int k) {
        // Map的key是共同的余数，只要余数相同，做差之后 就肯定是k的倍数
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        map.put(0, -1);
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            
            if (k != 0) { // 巧妙 使用同一个变量
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
    

    
    // All numbers are non-negative. 有负数的话这个双指针方法就不行了
    boolean subarraySumToTarget(int[] arr, int target) {
    	int[] sum = new int[arr.length + 1];
    	sum[0] = 0;
    	
    	for (int i = 1; i <= arr.length; i++) {
    		sum[i] = sum[i - 1] + arr[i - 1];
    	}
    	
    	int left = 0, right = 1; // From the same side
    	while (right <= arr.length) { // sum array is increasing order
    		int now = sum[right] - sum[left];
    		if (now == target) {
    			return true;
    		}
    		
    		if (now < target) {
    			right++;
    		} else {
    			left++;
    		}
    		
    		right = Math.max(right, left + 1);
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
    	ContinousSubarraySum subarraySum = new ContinousSubarraySum();
    	int[] arr = {-1, -3, -2, -5, 8, 6};
    	int target = 4;
    	System.out.println(subarraySum.subarraySumToTarget(arr, target));
    	System.out.println(subarraySum.subarraySumToTarget2(arr, target));
	}
}
