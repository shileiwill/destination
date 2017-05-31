package company.uber;
/**
 * 34. Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */
public class SearchForARange {

	public static void main(String[] args) {

	}

	/**
	 * Make sure the code is not so duplicate
	 * @param nums
	 * @param target
	 * @return
	 */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int left = binarySearchLeftBound(nums, target);
        if (left == -1) {
            return new int[]{-1, -1};
        }
        
        int right = binarySearchRightBound(nums, target, left);
        
        return new int[]{left, right};
    }
    
    int binarySearchLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else {
            return -1;
        }
    }
    
    int binarySearchRightBound(int[] nums, int target, int start) {
        int left = start, right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (target >= nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (nums[right] == target) {
            return right;
        } else if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

}
