package company.facebook;

import java.util.Arrays;

import leetcode2.string.LengthOfLastWord;
import sun.applet.Main;

/**
 * 300. Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 * @author Lei
 *
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // i 之前，并且以i结尾的字符，最长序列长度
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // 去前边遍历所有小于nums[i]的数，看看他们的frequency， 并且+1
                    hash[i] = Math.max(hash[i], hash[j] + 1);
                }
            }
        }
        
        // Find the maximum
        Arrays.sort(hash);
        
        return hash[nums.length - 1];
    }
    
    public int lengthOfLongestConsecutiveIncreasingSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // i 之前，并且以i结尾的字符，最长序列长度
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1;
            if (nums[i] > nums[i - 1]) { // 去前边遍历所有小于nums[i]的数，看看他们的frequency， 并且+1
                hash[i] = hash[i - 1] + 1;
            }
        }
        
        // Find the maximum
        Arrays.sort(hash);
        
        return hash[nums.length - 1];
    }
    
    public static void main(String[] args) {
    	LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    	int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
    	int res = lis.lengthOfLIS(arr);
    	System.out.println(res);
    	
    	int res2 = lis.lengthOfLongestConsecutiveIncreasingSequence(arr);
    	System.out.println(res2);
	}
}
