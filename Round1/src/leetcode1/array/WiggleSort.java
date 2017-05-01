package leetcode1.array;

import java.util.Arrays;

/**
 * 280. Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
 Notice

Please complete the problem in-place.

Have you met this question in a real interview? Yes
Example
Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {
	// In-place
    public void wiggleSort(int[] nums) {
         Arrays.sort(nums);
         for (int i = 1; i < nums.length - 1; i += 2) {
             swap(nums, i, i + 1);
         }
    }
    
    public void wiggleSortBetter(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Using extra space
    public void wiggleSort2(int[] nums) {
        int[] res = new int[nums.length];
        
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int index = 0;
        
        while (left <= right) {
            if (left == right) {
                res[index] = nums[left];
                break;
            }
            res[index++] = nums[left++];
            res[index++] = nums[right--];
        }
        
        for (int i = 0; i < res.length; i++) {
            nums[i] = res[i];
        }
    }

}
