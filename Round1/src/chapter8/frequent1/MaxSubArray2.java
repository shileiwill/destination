package chapter8.frequent1;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

 Notice

The subarray should contain at least one number

Have you met this question in a real interview? Yes
Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.
 * @author Lei
 *
 */
public class MaxSubArray2 {
		public static void main(String[] args) {
			
			Integer[] arr = {1,3,-1,2,-1,2};
			ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(arr));
			
		}
	    /**
	     * @param nums: A list of integers
	     * @return: An integer denotes the sum of max two non-overlapping subarrays
	     */
	    public int maxTwoSubArrays(ArrayList<Integer> nums) {
	        if (nums == null || nums.size() == 0) {
	            return 0;
	        }
	        
	        int[] left = new int[nums.size()];
	        int[] right = new int[nums.size()];
	        
	        // From Left to Right DP
	        int max = Integer.MIN_VALUE;
	        int minSum = 0;
	        int sum = 0;
	        for (int i = 0; i < left.length; i++) {
	            sum += nums.get(i);
	            max = Math.max(max, sum - minSum);
	            minSum = Math.min(minSum, sum);
	            left[i] = max;
	        }
	        
	        // From Right to Left DP
	        max = Integer.MIN_VALUE;
	        minSum = 0;
	        sum = 0;
	        for (int i = right.length - 1; i >= 0; i--) {
	            sum += nums.get(i);
	            max = Math.max(max, sum - minSum);
	            minSum = Math.min(minSum, sum);
	            right[i] = max;
	        }        
	        
	        // Merge. Find a place where 2 values together is maximum
	        int res = Integer.MIN_VALUE;
	        for (int i = 0; i < nums.size() - 1; i++) {
	            res = Math.max(res, left[i] + right[i + 1]);
	        }
	        
	        return res;
	    }
	    
	    // You can also start with the second element, but be careful with variable initialization
	    public int maxTwoSubArrays2(ArrayList<Integer> nums) {
	        if (nums == null || nums.size() == 0) {
	            return 0;
	        }
	        
	        // Left to right
	        int len = nums.size();
	        int[] left = new int[len]; // 直接存max sum
	        int[] right = new int[len];
	        
	        int curSum = nums.get(0);
	        int minSum = nums.get(0) < 0 ? nums.get(0) : 0; // There is a differece. either < 0 or 0
	        int maxInterval = nums.get(0);
	        
	        left[0] = nums.get(0);
	        for (int i = 1; i < len; i++) {
	            curSum += nums.get(i);
	            maxInterval = Math.max(maxInterval, curSum - minSum);
	            minSum = Math.min(minSum, curSum);
	                        
	            left[i] = maxInterval;
	        }
	        
	        // Right to left
	        curSum = nums.get(len - 1);
	        minSum = nums.get(len - 1) < 0 ? nums.get(len - 1) : 0;
	        maxInterval = nums.get(len - 1);
	        
	        right[len - 1] = nums.get(len - 1);
	        for (int i = len - 2; i >= 0; i--) {
	            curSum += nums.get(i);
	            maxInterval = Math.max(maxInterval, curSum - minSum);
	            minSum = Math.min(minSum, curSum);
	                        
	            right[i] = maxInterval;
	        }
	        
	        int res = Integer.MIN_VALUE;
	        for (int i = 0; i < len - 1; i++) {
	            int sum = left[i] + right[i + 1];
	            res = Math.max(res, sum);
	        }
	        
	        return res;
	    }

}
