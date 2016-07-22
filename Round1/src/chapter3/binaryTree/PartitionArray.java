package chapter3.binaryTree;
/**
 * Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

 Notice

You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length
 * @author Lei
 *
 */
public class PartitionArray {

	public static void main(String[] args) {
		int[] nums = {9,9,9,8,9,8,7,9,8,8,8,9,8,9,8,8,6,9};
		PartitionArray pa = new PartitionArray();
		int res = pa.partitionArray(nums, 9);
		
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " - ");
		}
	}
	
    public int partitionArray(int[] nums, int k) {
	    //write your code here
	    int res = partition(nums, 0, nums.length - 1, k);
	    return res;
    }
    
    int partition(int[] nums, int left, int right, int k) {
        while (left < right) {
            while (left < right && nums[left] < k) {
                left++;
            }
            while (left < right && nums[right] >= k) {
                right--;
            }
            // We have to do the swap to move forward
            swap(nums, left, right);
        }
        
        if (k > nums[left]) {
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
