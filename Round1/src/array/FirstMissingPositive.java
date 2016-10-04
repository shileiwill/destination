package array;

import java.util.Arrays;
/**
 * 41. Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        
        Arrays.sort(nums);
        // This is to use Binary search, calculation complexity is LogN. Just go through the Array is also fine, since it is already sorted. Time Complexity is N.
        int firstPositiveIndex = findFirstPositive(nums);
        if (firstPositiveIndex == -1) { // All negative numbers
            return 1;
        }
        
        int val = 1;
        for (int i = firstPositiveIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // Remove duplicate
                continue;
            }
            if (nums[i] != val) { // Found
                return val;
            }
            val++; // Move forward
        }
        return nums[nums.length - 1] + 1; // If numbers are increasing all the time, return the last + 1
    }
    
    int findFirstPositive(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] > 0) {
            return left;
        } else if (nums[right] > 0) {
            return right;
        } else {
            return -1;
        }
    }
    
    /**
     * This problem can solve by using a bucket-sort like algorithm. 
     * Let's consider finding first missing positive and 0 first. The key fact is that the ith element should be i, so we have:
		i==A[i]
		A[i]==A[A[i]]
     */
    public int firstMissingPositiveBucketSort(int[] nums) {
    	for (int i = 0; i < nums.length; i++) { // Can be only from 1 to N
    		while (nums[i] != (i + 1)) { // From 1
    			if (nums[i] <= 0 || nums[i] > nums.length) { // OutOfBound
    				break;
    			}
    			
    			if (nums[i] == nums[nums[i] - 1]) { // Already in place, NO! Wrong! It is to handle duplicate elements, just ignore, jump
    				break;
    			}
    			
    			swap(nums, i, nums[i] - 1);
    		}
    	}
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] != i + 1) {
    			return i + 1;
    		}
    	}
    	
    	return nums.length + 1;
    }
    
    // Above one is a little difficult to understand because it doesnt include 0.
    int firstMissingPositiveAnd0(int A[]) {
    	int n = A.length;
    	for (int i = 0; i < n; i++) {
    		// when the ith element is not i
    		while (A[i] != i) {
    			// no need to swap when ith element is out of range [0,n]
    			if (A[i] < 0 || A[i] >= n)
    				break;
     
    			//handle duplicate elements, just ignore, jump
    			if(A[i]==A[A[i]])
                    break;
    			// swap elements
    			int temp = A[i];
    			A[i] = A[temp];
    			A[temp] = temp;
    		}
    	}
     
    	for (int i = 0; i < n; i++) {
    		if (A[i] != i)
    			return i;
    	}
     
    	return n;
    }
    
	private void swap(int[] N, int left, int right) {
		int temp = N[left];
		N[left] = N[right];
		N[right] = temp;
	}
    
}