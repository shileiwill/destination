package array;

import java.util.Arrays;

/**
 * 268. Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class FindMissingNumber {
    public int missingNumber2(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        
        return res;
    }
    
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2; // 等差数列求和公式 (a1 + an) * n / 2
        
        for (int val : nums) {
            sum -= val;
        }
        
        return sum;
    }
    
    // Using Binary Search
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else if (nums[mid] <= mid) {
                left = mid;
            }
        }
        
        if (nums[left] != left) {
            return left;
        }
        
        return right;
    }
}
