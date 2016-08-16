package chapter8.frequent;
/**
 * 53. Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * @author Lei
 *
 */
public class MaxSubArray {
    // The Divide and Conquer solution
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int res = dfsHelper(nums, 0, nums.length - 1);
        return res;
    }
    
    //分两类情况，一是左边或者右边单独是max，二是左边右边一起组成max
    int dfsHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        int leftMax = dfsHelper(nums, left, mid);
        int rightMax = dfsHelper(nums, mid + 1, right);
        
        int lSum = 0;
        int lMaxSum = Integer.MIN_VALUE;
        for (int i = mid; i >= 0; i--) {
            lSum += nums[i];
            lMaxSum = Math.max(lMaxSum, lSum);
        }
        
        int rSum = 0;
        int rMaxSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            rSum += nums[i];
            rMaxSum = Math.max(rSum, rMaxSum);
        }
        
        int crossMax = lMaxSum + rMaxSum;
        
        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }
    
    /*
    In this problem every element in the list can be considered as the difference between two stock prices in two consecutive days. The O(n) solution is the same idea as the solution to the stock price problem
    */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Build a sum array
        int[] sum = new int[nums.length + 1];
        
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        // Find max Profit in sum array
        int min = sum[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < sum.length; i++) {
            res = Math.max(res, sum[i] - min);
            min = Math.min(min, sum[i]);
        }
        
        return res;
    }
}
