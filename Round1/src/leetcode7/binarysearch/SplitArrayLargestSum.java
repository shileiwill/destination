package leetcode7.binarysearch;
/**
 * 410. Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
	    public int splitArray(int[] nums, int m) {
	        int maxVal = 0;
	        long sum = 0;
	        
	        for (int num : nums) {
	            maxVal = Math.max(maxVal, num);
	            sum += num;
	        }
	        
	        long left = maxVal, right = sum;
	        while (left <= right) {
	            long mid = left + (right - left) / 2;
	            if (isValid(mid, nums, m)) {
	                right = mid - 1;
	            } else {
	                left = mid + 1;
	            }
	        }
	        // Left and right may not be a valid sum?
	        return (int)left; // Return the bigger one
	        /*
	        https://discuss.leetcode.com/topic/61315/java-easy-binary-search-solution-8ms/2
	        you are returning 18, but how did you make sure 18 is a sum of subarray ?
	        i'm not sure if it is possible : every sum of subarray is small than 18 but NO equals.
	        If none sum of the subarrays is 18, then there exists a sum that is the largest sum of these subarrays, say, 17. The binary search returns the FIRST one that is feasible, so 17 will be returned instead.
	        */
	    }
	    
	    boolean isValid(long val, int[] nums, int m) {
	        int count = 1; // It is 1 by default
	        int total = 0;
	        
	        for (int num : nums) {
	            total += num;
	            
	            if (total > val) {
	                total = num;
	                count++;
	                
	                if (count > m) {
	                    return false;
	                }
	            }
	        }
	        
	        return true;
	    }
}
