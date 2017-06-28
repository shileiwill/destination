package company.facebook;
/**
 * 238. Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of 
 * all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptItself {
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
       
        int[] leftToRight = new int[len]; // The result is the product of previous ones, but doesnt include itself
        int[] rightToLeft = new int[len];
        int[] res = new int[len];
       
        leftToRight[0] = 1;
        rightToLeft[len - 1] = 1;
       
        for (int i = 1; i < len; i++) {
            leftToRight[i] = leftToRight[i - 1] * nums[i - 1];
        }
        
        for (int i = len - 2; i >= 0; i--) {
            rightToLeft[i] = rightToLeft[i + 1] * nums[i + 1];
        }
       
        for (int i = 0; i < len; i++) {
            res[i] = leftToRight[i] * rightToLeft[i];
        }
       
        return res;
    }
   
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1; // Doesn't include itself
       
        // Left to right scan
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
       
        int right = 1;
        // Right to left scan
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= right; // res[] is the left side
            right *= nums[i]; // Rebuild the right side
        }
       
        return res;
    }
}
