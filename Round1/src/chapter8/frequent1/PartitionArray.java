package chapter8.frequent1;
/**
 * Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

 Notice

You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length

Have you met this question in a real interview? Yes
Example
If nums = [3,2,2,1] and k=2, a valid answer is 1.
 * @author Lei
 *
 */
public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
	    //write your code here
	    if (nums == null || nums.length == 0) {
	        return 0;
	    }
	    int res = partition(nums, k, 0, nums.length - 1);
	    return res;
    }
    
    int partition(int[] nums, int k, int left, int right) {
        while (left < right) { // No need to do recursion
            while (left < right && nums[left] < k) {
                left++;
            }
            
            while (left < right && nums[right] >= k) {
                right--;
            }
            
            swap(nums, left, right);
        }
        
        if (nums[left] < k) {
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
