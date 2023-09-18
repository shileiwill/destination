package chapter5.dp;
/**
 * 198. You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber1 {
    // Solution 1: Tabulation. Use an array to track the previous outcomes
    public int rob(int[] nums) {
        int[] hash = new int[nums.length];
        
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        
        hash[0] = nums[0];
        hash[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            hash[i] = Math.max(hash[i - 1], hash[i - 2] + nums[i]);
        }
        
        return hash[nums.length - 1];
    }
    
    // Solution 2: Tabulation. What if array is not allowed, just track the immediate 2 prior outcomes
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        
        return second;
    }

    // Solution 3: Recursion, without memoization
    public int rob(int[] nums) {
        return helper(nums, 0);
    }

    int helper(int[] nums, int pos) {
        if (pos >= nums.length) {
            return 0;
        }

        // Either take current or not
        return Math.max(helper(nums, pos + 1), helper(nums, pos + 2) + nums[pos]);
    }

    // Solution 4: Memoization
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        // this is just to avoid all 0s, an edge case
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        return helper(nums, 0, memo);
    }

    int helper(int[] nums, int pos, int[] memo) {
        if (pos >= nums.length) {
            return 0;
        }

        if (memo[pos] != -1) {
            return memo[pos];
        }

        // Either take current or not
        int max = Math.max(helper(nums, pos + 1, memo), helper(nums, pos + 2, memo) + nums[pos]);
        memo[pos] = max;

        return max;
    }

    
}
