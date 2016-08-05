package chapter8.frequent1;
/**
 * 53. Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * @author Lei
 *
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
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
