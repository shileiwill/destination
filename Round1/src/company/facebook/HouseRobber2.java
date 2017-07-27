package company.facebook;
/**
 * 213. Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery 
so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber2 {
    public int rob(int[] nums) {
        int[] hash = new int[nums.length];
        int[] hash2 = new int[nums.length];
        
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // Rob the first house, 0 -> nums.length - 2
        hash[0] = nums[0];
        hash[1] = Math.max(nums[0], nums[1]); // 还是得比较一下的，记住！
        
        for (int i = 2; i <= nums.length - 2; i++) {
            hash[i] = Math.max(hash[i - 1], hash[i - 2] + nums[i]);
        }
        
        // Don't Rob the first house, 1 -> nums.length - 1
        hash2[1] = nums[1];
        hash2[2] = Math.max(nums[1], nums[2]); // 初始化两个
        
        for (int i = 3; i <= nums.length - 1; i++) {
            hash2[i] = Math.max(hash2[i - 1], hash2[i - 2] + nums[i]);
        }
        
        return Math.max(hash[nums.length - 2], hash2[nums.length - 1]);
    }
}
