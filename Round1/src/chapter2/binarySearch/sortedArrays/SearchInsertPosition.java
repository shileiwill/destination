package chapter2.binarySearch.sortedArrays;
/**
 * 35. Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 : 2
[1,3,5,6], 2 : 1
[1,3,5,6], 7 : 4
[1,3,5,6], 0 : 0
 * @author Lei
 *
 */
public class SearchInsertPosition {
	// Find the first element which is >= target
	// Find the first position of target
	// Find the last position of target
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        // Here need to cover the equal cases, target == left or target == right
        if (target <= nums[left]) {
            return left;
        } else if (target > nums[right]) {
            return right + 1;
        } else {
            return right;
        }
    }
}
