package company.amazon.lintcode;
/**
 * Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, 
 * find the sum of the element inside the window at each moving.
For array [1,2,7,8,5], moving window size k = 3. 
1 + 2 + 7 = 10
2 + 7 + 8 = 17
7 + 8 + 5 = 20
return [10,17,20]
 */
public class WindowSum {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
       
        int[] res = new int[nums.length - k + 1];
       
        if (nums.length < k) {
            return res;
        }
       
        int sum = 0;
        int left = 0;
        int right = k;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
       
        while (right < nums.length) {
            res[right - k] = sum;
            sum = sum - nums[left] + nums[right];
            left++;
            right++;
        }
        res[right - k] = sum; // Dont forget the last one
       
        return res;
    }
}