package company.facebook;

public class MaxProductSubarray {

	public int maxProduct(int[] nums) {
		int[] min = new int[nums.length];
		int[] max = new int[nums.length];
		min[0] = nums[0];
		max[0] = nums[0];
		int res = Integer.MIN_VALUE;
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < 0) {
				min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
				max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
				
				res = Math.max(res, max[i]);
			} else {
				min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
				max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
				
				res = Math.max(res, max[i]);
			}
		}
		
		return res;
	}
	
	boolean targetProductMyStyle(int[] arr, int target) {
		int left = 0, right = 0;
		int flag = target < 0 ? -1 : 1;
		target = Math.abs(target);
		
		int sign = 1; // Keep track of the sign
		int now = 1; // now, doesnt care about the sign
		
		while (right < arr.length) {
			int rightVal = arr[right];
			if (rightVal < 0) {
				sign = -sign;
				rightVal = -rightVal;
			}
			now *= rightVal;
			right++;
			
			while (now > target) {
				int leftVal = arr[left];
				if (leftVal < 0) {
					sign = -sign;
					leftVal = -leftVal;
				}
				now /= leftVal;
				left++;
			}
			
			if (now == target) {
				if (flag == sign) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		MaxProductSubarray p = new MaxProductSubarray();
		
		int[] nums = {1, -2, 3, 4, 3, 7};
		int target = -24;
		
		System.out.println(p.targetProductMyStyle(nums, target));
	}
}
