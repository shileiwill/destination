package chapter8.frequent;
/**
 * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

 Notice

You are not suppose to use the library's sort function for this problem.

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
 */
public class Partition_SortColors2 {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        
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
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}