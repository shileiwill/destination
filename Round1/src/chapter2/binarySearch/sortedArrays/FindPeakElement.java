package chapter2.binarySearch.sortedArrays;
/**
 * 162. A peak element is an element that is greater than its neighbors.

Given an input array where num[i] != num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -unlimited small.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * @author Lei
 *
 */
public class FindPeakElement {
	// This is a better version
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // We dont need to worry about mid == 0 or length - 1. Because (left + 1 < right) makes elements never overlap.
            // It stops when 2 elements sit next to each other. So, mid will never be 0 or length - 1
            if ((nums[mid] > nums[mid - 1]) && (nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                right = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid;
            }
        }
        // If there is only 1 or 2 elements, come directly here.
        if (nums[right] > nums[left]) {
            return right;
        }
        return left;
    }
    
    public int findPeakElementVersion1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (mid > 0 && nums[mid] < nums[mid - 1]) {
                right = mid;
            } else if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                left = mid;
            }
        }
        
        if (nums[right] > nums[left]) {
            return right;
        }
        return left;
    }
}
