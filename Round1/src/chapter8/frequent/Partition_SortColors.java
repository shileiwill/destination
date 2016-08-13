package chapter8.frequent;
/**
 * 75. Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
 * @author Lei
 *
 */
public class Partition_SortColors {
    public void sortColors(int[] nums) {
	    if (nums == null || nums.length == 0) {
	        return;
	    }
	    partition(nums, 0, nums.length - 1);
    }
    
    void partition(int[] nums, int left, int right) {
        // Partition 0 and non-0
        while (left < right) {
            while (left < right && nums[left] == 0) {
                left++;
            }
            
            while (left < right && nums[right] != 0) {
                right--;
            }
            
            swap(nums, left, right);
        }
        
        if (nums[left] == 0) {
            left++;
        }
        // Partition 1 and 2
        right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] == 1) {
                left++;
            }
            
            while (left < right && nums[right] == 2) {
                right--;
            }
            
            swap(nums, left, right);
        }
    }
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
