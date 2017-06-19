package company.amazon;
/**
 * 一个non-negative integer array里找subarray sum。
(followup: 数字可以为负)
 */
public class SubarraySum {

	public static void main(String[] args) {
		int[] arr = {1, 3, 2, 5, 6, 9, 4, 2};
		subarraySum(arr, 18);
	}

	// If non-negative, then sum array is increasing, so we can use 2 pointers
	static void subarraySum(int[] arr, int target) {
		int len = arr.length;
		int[] sum = new int[len + 1];
		sum[0] = 0;
		
		for (int i = 1; i <= len; i++) {
			sum[i] = sum[i - 1] + arr[i - 1];
		}
		
		int left = 0, right = 1;
		while (left < sum.length && right < sum.length) {
			if (left == right) {
				right++;
				continue;
			}
			
			int now = sum[right] - sum[left];
			if (now == target) {
				System.out.println(left + "====true====" + right);
				return;
			}
			
			if (now < target) {
				right++;
			} else {
				left++;
			}
		}
	}
}
