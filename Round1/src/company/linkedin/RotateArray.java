package company.linkedin;
/**
 * 189. Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II
 */
public class RotateArray {

	public static void main(String[] args) {
		RotateArray ra = new RotateArray();
		int[] nums = {1, 2, 3, 4, 5, 6, 7};
		ra.rotate(nums, 13);
	}

	public void rotate(int[] nums, int k) {
		int len = nums.length;
		k = k % len;
		
		reverse(nums, 0, len - 1); // All
		reverse(nums, 0, k - 1); // Left
		reverse(nums, k, len - 1); // Right
		
		for (int val : nums) {
			System.err.println(val);
		}
	}
	
	void reverse(int[] nums, int left, int right) {
		while (left < right) {
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;
			
			left++;
			right--;
		}
	}
}
