package chapter2.sortedArrays;

import java.util.Arrays;
/**
 * 88. Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * @author Lei
 *
 */
public class MergeSortedArrays {

	public static void main(String[] args) {
		MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();
		int[] nums1 = {0};
		int[] nums2 = {1};
		int m = 0;
		int n = 1;
		mergeSortedArrays.merge(nums1, m, nums2, n);
	}

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
    	// Can we do it without using extra space?
        int[] nums = new int[m + n];
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        // Compare, move one by one
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        
        // Leftover in nums1
        while (i < m) {
            nums[k++] = nums1[i++];
        }
        
        // Leftover in nums2
        while (j < n) {
            nums[k++] = nums2[j++];
        }
        
        for (int m1 = 0; m1 < nums.length; m1++) {
            nums1[m1] = nums[m1];
        }
    }
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	// To avoid moving arrays, let's do it from the very end.
    	int pos = m + n - 1;
    	int i = m - 1;
    	int j = n - 1;
    	
    	while (i >= 0 && j >= 0) {
    		if (nums1[i] >= nums2[j]) {
    			nums1[pos--] = nums1[i--];
    		} else {
    			nums1[pos--] = nums2[j--];
    		}
    	}
    	
    	// Take care of only nums2
    	while (j >= 0) {
    		nums1[pos--] = nums2[j--];
    	}
    	
    }
}
