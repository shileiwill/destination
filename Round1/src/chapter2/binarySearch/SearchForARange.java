package chapter2.binarySearch;

/**
 * 34. Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 * @author Lei
 *
 */
public class SearchForARange {

	public static void main(String[] args) {
		SearchForARange searchForARange = new SearchForARange();
		int[] nums = {1};
		
		int first = searchForARange.findFirstElement(nums, 1);
		int last = searchForARange.findLastElement(nums, 1);
		System.out.println(first + "--" + last);
	}
	
	private int findFirstElement(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int left = 0;
		int right = nums.length - 1;
		
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (nums[mid] == target) {
				right = mid; // Continue searching on left
			} else if (nums[mid] > target) {
				right = mid;
			} else if (nums[mid] < target) {
				left = mid;
			}
		}
		
		if (nums[left] == target) { // Left comes first
			return left;
		}
		if (nums[right] == target) {
			return right;
		}
		
		return -1;
	}

	private int findLastElement(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int left = 0;
		int right = nums.length - 1;
		
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (nums[mid] == target) {
				left = mid; // Continue searching on right
			} else if (nums[mid] > target) {
				right = mid;
			} else if (nums[mid] < target) {
				left = mid;
			}
		}
		
		if (nums[right] == target) { // Right comes first
			return right;
		}
		if (nums[left] == target) {
			return left;
		}
		
		return -1;
	}
}
