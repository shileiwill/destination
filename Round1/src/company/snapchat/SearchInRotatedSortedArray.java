package company.snapchat;
//重要
/**
 * 33. Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

题意是在数组里找到与目标值相差最小的数，比如{3, 6, 8, 1, 2} 目标值是5时，就应该返回6，因为它们只相差1
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
    
    // A new version on 02/04/2017
    // 81. what if duplicates are allowed
    // The worst case will be O(n), so we can also use just a linear for loop.
	// For example, 1 1 1 1 1 1 1 1 1 1, while target is 0
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // left side could be 1) same 2) ascending 3) descending
            if (nums[mid] == nums[left]) { // The only change compared with non-duplicate is here. just move a step further
                left++;
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
