package company.facebook;
/**
 * 75. Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

给三个funtions: is_low(), is_mid(), is_high(). 让给一个数组排序, low的放在最前面, mid的放在中间, high的放在最后面.
Color sort: think about when there are K colors
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

    /*
     * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, 
     * with the colors in the order 1, 2, ... k.
You are not suppose to use the library's sort function for this problem.

k <= n

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?
     */
    // Quick Sort
    public void sortColors2(int[] colors, int k) {
        int left = 0;
        for (int i = 1; i <= k; i++) {
            left = partition(colors, left, colors.length - 1, i);
        }
    }
    
    int partition(int[] nums, int left, int right, int val) {
        while (left < right) {
            while (left < right && nums[left] == val) {
                left++;
            }
            while (left < right && nums[right] != val) {
                right--;
            }
            swap(nums, left, right);
        }
        if (nums[left] == val) {
            return left + 1;
        } else {
            return left;
        }
    }
}
