package leetcode8.dynamicprogramming;
/**
 * 376. A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?
 */
public class WiggleSubsequence {
    // https://leetcode.com/articles/wiggle-subsequence/
    public int wiggleMaxLengthBruteForce(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        
        return 1 + Math.max(helper(nums, 0, true), helper(nums, 0, false));
    }
    
    int helper(int[] nums, int start, boolean isUp) {
        int maxCount = 0;
        
        for (int i = start + 1; i < nums.length; i++) {
            if ((isUp && nums[i] > nums[start]) || (!isUp && nums[i] < nums[start])) {
                maxCount = Math.max(maxCount, helper(nums, i, !isUp) + 1);
            }
        }
        
        return maxCount;
    }
    
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        
        // Why need to add 1
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
