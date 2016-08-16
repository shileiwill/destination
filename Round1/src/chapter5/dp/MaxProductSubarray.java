package chapter5.dp;
/**
 * 152. Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 * @author Lei
 *
 */
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        
        if (len == 0) {
            return 0;
        }
        
        int[] max = new int[len];
        int[] min = new int[len];
        
        // for (int i = 0; i < len; i++) { // Will initialize in DP
        //     hash[i][i] = nums[i];
        // }
        
        max[0] = nums[0];
        min[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            max[i] = nums[i];
            min[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) { // Never talk about == 0
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }
            
            res = Math.max(res, max[i]);
        }
        
        return res;
    }
    
    // As we are using only previous elements, we can remove the array
    public int maxProduct2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int size = nums.length;
        
        int prevMax = nums[0];
        int prevMin = nums[0];
        
        int res = nums[0];
        for (int i = 1; i < size; i++) {
            int curMax = nums[i]; // 他自己 Initialization
            int curMin = nums[i];
            if (nums[i] < 0) {
                curMax = Math.max(curMax, prevMin * nums[i]);
                curMin = Math.min(curMin, prevMax * nums[i]);
            } else { // We dont care about 0. all will be 0
                curMax = Math.max(curMax, prevMax * nums[i]);
                curMin = Math.min(curMin, prevMin * nums[i]);
            }
            
            prevMax = curMax;
            prevMin = curMin;
            
            res = Math.max(res, curMax);
        }
        
        return res;
    }
}
