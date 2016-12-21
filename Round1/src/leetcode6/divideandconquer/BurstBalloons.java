package leetcode6.divideandconquer;
/**
 * 312. Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
    // Reference: https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
    // D & C
    public int maxCoinsDC(int[] nums) {
        int[] arr = new int[nums.length + 2];
        int n = 1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) { // Filter 0 out as it can't contribute to final result
                arr[n++] = nums[i];
            }
        }
        
        // Make the start and the end as 1, n is the effective length
        arr[0] = arr[n++] = 1;
        int[][] cache = new int[n][n];
        
        int res = helper(arr, cache, 0, n - 1);
        
        return res;
    }
    
    int helper(int[] nums, int[][] cache, int left, int right) {
        if (left + 1 == right) { // Only 2 boundries left
            return 0;
        }
        
        if (cache[left][right] > 0) {
            return cache[left][right];
        }
        
        int max = 0;
        for (int i = left + 1; i < right; i++) {
            // i is the one which will burst at the end. The 2 boundries, left, right, are not included
            max = Math.max(max, nums[left] * nums[i] * nums[right] + helper(nums, cache, left, i) + helper(nums, cache, i, right));
        }
        
        cache[left][right] = max;
        
        return max;
    }
    
    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        int n = 1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) { // Filter 0 out as it can't contribute to final result
                arr[n++] = nums[i];
            }
        }
        
        // Make the start and the end as 1, n is the effective length
        arr[0] = arr[n++] = 1;
        int[][] dp = new int[n][n];
        
        for (int k = 2; k < n; k++) { // Distance
            for (int left = 0; left < n - k; left++) {
                int right = left + k;
                for (int i = left + 1; i < right; i++) { // {left, right}中的每一个都可以最后burst
                    dp[left][right] = Math.max(dp[left][right], arr[left] * arr[i] * arr[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}