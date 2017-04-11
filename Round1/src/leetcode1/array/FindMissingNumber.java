package leetcode1.array;

import java.util.Arrays;

import sun.applet.Main;

/**
 * 268. Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class FindMissingNumber {
	public static void main(String[] args) {
		FindMissingNumber miss = new FindMissingNumber();
		
		int[] nums = {2, 0};
		int res = miss.missingNumber4(nums);
		System.out.println(res);
	}
	
    public int missingNumber4(int[] nums) {
        boolean value0IsGood = false;
        boolean hasN = false;
        
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            
            if (val == nums.length) {
                hasN = true;
            } else {
                if (nums[val] > 0) {
                    nums[val] = -nums[val];
                } else if (nums[val] == 0) {
                    value0IsGood = true;
                }
                // If already negative, which means this number appears several times, just move on
            }
        }
        
        if (!hasN) {
            return nums.length;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            } else if (nums[i] == 0 && !value0IsGood) {
                return i;
            }    
        }
        
        return -1;
    }
    
    // Bucket
    public int missingNumber3(int[] nums) {
        int len = nums.length;
        int[] bucket = new int[len + 1];
        
        for (int i = 0; i < len; i++) {
            bucket[nums[i]]++;
        }
        
        for (int i = 0; i < len + 1; i++) {
            if (bucket[i] == 0) {
                return i;
            }
        }
        
        return -1;
    }
    
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
