package leetcode20.math;

import java.util.Arrays;

/**462.
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class MinimumMovesToEqualArrayElements2 {

    // https://discuss.leetcode.com/topic/68736/java-just-like-meeting-point-problem/5
    // Find the mid point
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        
        int mid = nums.length / 2;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[mid]) { // i > mid will also work
                res += nums[i] - nums[mid];
            } else {
                res += nums[mid] - nums[i];
            }
        }
        
        return res;
    }

}
