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

}
