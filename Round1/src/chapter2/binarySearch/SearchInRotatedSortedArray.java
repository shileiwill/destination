package chapter2.binarySearch;
/**
 * 33. Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 * @author Lei
 *
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] > nums[left]) {// Left hand in order
                if (target < nums[mid] && target >= nums[left]) { // Stay on left
                    right = mid;
                } else { // Go to the disorder
                    left = mid;
                }
            } else { // Right in order
                if (target > nums[mid] && target <= nums[right]) { // Stay on right
                    left = mid;
                } else { // Go to the disorder
                    right = mid;
                }
            }
        }
        
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        
        return -1;
    }
}
