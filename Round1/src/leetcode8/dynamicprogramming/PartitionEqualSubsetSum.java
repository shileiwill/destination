package leetcode8.dynamicprogramming;
/**
 * 416. Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    // 0-1 Backpack
    // https://discuss.leetcode.com/topic/67539/0-1-knapsack-detailed-explanation
    public boolean canPartition(int[] nums) {
        int sum = 0;
        
        for (int num : nums) {
            sum += num;
        }
        
        if ((sum & 1) == 1) {
            return false;
        }
        
        sum = sum / 2;
        
        boolean[][] hash = new boolean[nums.length + 1][sum + 1];
        
        for (int i = 0; i < hash.length; i++) {
            hash[i][0] = true;
        }
        
        for (int j = 1; j < hash[0].length; j++) {
            hash[0][j] = false;
        }
        
        for (int i = 1; i < hash.length; i++) {
            for (int j = 1; j < hash[0].length; j++) {
                hash[i][j] = hash[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    hash[i][j] = hash[i][j] || hash[i - 1][j - nums[i - 1]];
                }
            }
        }
        
        return hash[nums.length][sum];
    }
}
