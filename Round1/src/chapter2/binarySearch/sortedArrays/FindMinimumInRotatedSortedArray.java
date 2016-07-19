package chapter2.binarySearch.sortedArrays;
/**
 * 153. Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
 * @author Lei
 *
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[left]) {// Left hand in order
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
