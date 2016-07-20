package chapter2.binarySearch;

public class BinarySearchTemplate {

	public static void main(String[] args) {
		int[] nums = {1, 2, 4, 6, 8, 12,19};
//		int res = binarySearchWhile(nums, 2);
		int res = binarySearchRecursion(nums, 2, 0, nums.length - 1);
		System.out.println(res);
	}
	
	static int binarySearchRecursion(int[] nums, int target, int left, int right) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		if (left + 1 < right) { // Deliberately dont overlap, will add judgement later
			int mid = left + (right - left) / 2; // To avoid it is too huge if we do (left + right) / 2
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				return binarySearchRecursion(nums, target, mid, right);
			} else if (nums[mid] > target) {
				return binarySearchRecursion(nums, target, left, mid);
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

	static int binarySearchWhile(int[] nums, int target) {
		
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int left = 0;
		int right = nums.length - 1;
		
		while (left + 1 < right) { // Deliberately dont overlap, will add judgement later
			int mid = left + (right - left) / 2; // To avoid it is too huge if we do (left + right) / 2
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid;
			} else if (nums[mid] > target) {
				right = mid;
			}
		}
		
		// Either find the first occurance or the last occurance
		if (nums[left] == target) {
			return left;
		}
		if (nums[right] == target) {
			return right;
		}
		
		return -1;
	}
}
