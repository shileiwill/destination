package company.uber;
/**
 * 75. Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {

	public static void main(String[] args) {

	}


    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int pos = helper(nums, 0, nums.length - 1, 0);
        helper(nums, pos, nums.length - 1, 1);
    }
    
    int helper(int[] nums, int start, int end, int val) {
        int left = start, right = start;
        
        while (right <= end) {
            while (left <= end && nums[left] == val) {
                left++;
            } // Left will be the first non-val
            
            right = Math.max(right, left);
            while (right <= end && nums[right] != val) {
                right++;
            } // Right will be the first val
            
            if (left < right && right <= end) {
                swap(nums, left, right);
            }
        }
        
        return left;
    }
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
