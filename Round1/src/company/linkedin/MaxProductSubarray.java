package company.linkedin;

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
	
	boolean targetProduct(int[] nums, int target) {
		int left = 0, right = 0;
		int sign = 1; // Keep track of the sign
		int now = 1; // now, doesnt care about the sign
		
		while (left <= right && right < nums.length) {
			while (now != 0 && right < nums.length && now < Math.abs(target)) {
				now = Math.abs(now * nums[right]);
				sign = nums[right] < 0 ? -sign : sign;
				right++;
			}

			if (now == 0) {
				if (target == 0) {
					return true;
				}
				left = right; // Reset
				now = 1;
				sign = 1;
			} else if (now == Math.abs(target)) {
				if (target * sign > 0) {
					return true;
				}
				now = now / Math.abs(nums[left]);
				sign = nums[left] < 0 ? -sign : sign;
				left++;
			} else if (now > Math.abs(target)) {
				now = now / Math.abs(nums[left]);
				sign = nums[left] < 0 ? -sign : sign;
				left++;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		MaxProductSubarray p = new MaxProductSubarray();
		
		int[] nums = {1, -2, 3, 4, -5, 6};
		int target = 24;
		
		System.out.println(p.targetProduct(nums, target));
	}
}
