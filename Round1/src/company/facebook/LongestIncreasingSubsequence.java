package company.facebook;

import java.util.Arrays;

/**
 * 300. Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, 
it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 *
 */
public class LongestIncreasingSubsequence {
	
    public static void main(String[] args) {
    	LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    	int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
    	int res = lis.lengthOfLIS(arr);
    	System.out.println(res);
    	
    	int res2 = lis.lengthOfLongestConsecutiveIncreasingSequence(arr);
    	System.out.println(res2);
	}
    
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int max = 0;
        
        // i 之前，并且以i结尾的字符，最长序列长度
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // 去前边遍历所有小于nums[i]的数，看看他们的frequency， 并且+1
                    hash[i] = Math.max(hash[i], hash[j] + 1); // 必须得遍历，因为数组是unsorted.
                }
            }
            max = Math.max(max, hash[i]);
        }
        
        return max;
    }
    
    // This question is very similar to Longest Increasing Subsequence, but easier
    /**
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

	Formally the function should:
	Return true if there exists i, j, k 
	such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
	Your algorithm should run in O(n) time complexity and O(1) space complexity.
	
	Examples:
	Given [1, 2, 3, 4, 5],
	return true.
	
	Given [5, 4, 3, 2, 1],
	return false.
	
	To achieve O(n) time, you can use TreeMap
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    hash[i] = Math.max(hash[i], hash[j] + 1);
                    if (hash[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    // O(N)
    public boolean increasingTripletN(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
    
    // Wrong 哪里错了？ 我看没错， 好题
    public int lengthOfLongestConsecutiveIncreasingSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // i 之前，并且以i结尾的字符，最长递增连续序列长度
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1; // 默认是他自己
            if (nums[i] > nums[i - 1]) { // 去前边遍历所有小于nums[i]的数，看看他们的frequency， 并且+1
                hash[i] = hash[i - 1] + 1; // Will not work, need to loop。 因为我们只关心连续递增，所以没问题
            }
        }
        
        // Find the maximum. 完全可以在上边的for循环里把max记录一下的
        Arrays.sort(hash);
        
        return hash[nums.length - 1];
    }
    
    // int[] arr = {3, 1, 4, 5, 6, 8, 9, 10, 11}; return 4
	int findLongestConsecutive(int[] arr) {
		int max = 1;
		int len = 1;
		
		for (int i = 1; i < arr.length;) {
			while (i < arr.length && arr[i] == arr[i - 1] + 1) {
				len++;
				i++;
			}
			max = Math.max(len, max);
			
			if (i < arr.length) { // Still have other possibilities
				len = 1; // Save current char, reset
				i++; // Go to next char
			}
		}
		
		return max;
	}
}
