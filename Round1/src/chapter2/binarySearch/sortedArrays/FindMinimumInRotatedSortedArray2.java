package chapter2.binarySearch.sortedArrays;
/**
 * 154. Follow up for 153 as shown below. What if there are duplicates. What is the worst case complexity? 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
 * @author Lei
 *
 */
public class FindMinimumInRotatedSortedArray2 {
	// The worst case will be O(n), so we can also use just a linear for loop.
	// For example, 1 1 1 1 1 1 1 1 1 1
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
                left = left + 1;
                right = right - 1;
                min = Math.min(min, nums[mid]);
            } else if (nums[mid] == nums[left]) {
                left = mid;
                min = Math.min(min, nums[mid]);
            } else if (nums[mid] == nums[right]) {
                right = mid;
                min = Math.min(min, nums[mid]);
            } else if (nums[mid] > nums[left]) {// Left hand in order
                min = Math.min(min, nums[left]);
                // Go to the disorder
                left = mid;
            } else { // Right in order
                min = Math.min(min, nums[mid]);
                right = mid;
            }
        }
        
        return Math.min(Math.min(nums[left], nums[right]), min);
    }
}
