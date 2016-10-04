package array;

import java.util.Arrays;

/**
 * 287. Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber {
    public int findDuplicate3(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        
        return -1;
    }
    
    // Binary search in unsorted array, not easy to understand
    public int findDuplicate2(int[] nums) {
        int left = 1; // Each integer is between 1 to N
        int right = nums.length - 1; // Since there is N + 1 elements, our right is N, so nums.length - 1
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            int countOfSmaller = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    countOfSmaller++;
                }
            }
            
            if (countOfSmaller <= mid) { // Small numbers are too few, so duplicates are on the right
                left = mid + 1; // If using left < right, must + 1 somewhere
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // Floyd's cycle detection
    // This approach essentially creates a graph out of the array (outgoing edge from each array value to the index denoted by that array value). Then, you run Floyd's cycle finding algorithm (tortoise and hare) to find the cycle (duplicate value in array)
    public int findDuplicate(int[] nums) {
        int tortoise = 0; // Slow
        int hare = 0; // Fast
        
        while (true) {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]]; // Jumped twice
            
            if (tortoise == hare) {
                break;
            }
        }
        
        int finder = 0; // Start from the very begining point
        while (true) {
            tortoise = nums[tortoise];
            finder = nums[finder];
            
            if (tortoise == finder) {
                break;
            }
        }
        
        return finder;
    }
}
