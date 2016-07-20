package chapter2.binarySearch;
/**
 * 81. What if duplicates are allowed?
 * @author Lei
 *
 */
public class SearchInRotatedSortedArray2 {
	// The worst case will be O(n), so we can also use just a linear for loop.
	// For example, 1 1 1 1 1 1 1 1 1 1, while target is 0
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Use left, right. Dont use 0, nums.length - 1, as left right ends are changing
            if (nums[mid] == nums[left] && nums[mid] == nums[right]) { // Search both sides, just decrease the range
                left = left + 1;
                right = right - 1;
            } else if (nums[mid] == nums[left]) { // Left are all the same, no doubt, go to right
                left = mid;
            } else if (nums[mid] == nums[right]) {
                right = mid;                
            } else if (nums[mid] > nums[left]) {// Left hand in order
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
            return true;
        }
        if (nums[right] == target) {
            return true;
        }
        
        return false;
    }
}
