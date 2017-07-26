package company.facebook.others;

/**
 * Created by weixwu on 7/4/2017.
 */
public class SortKColors {
	public void sortKColors(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;
		int minColor = 1;
		int maxColor = k;
		
		while (minColor < maxColor) {
			for (int i = left; i <= right; i++) {
				while (nums[i] == maxColor && i < right) {
					swap(nums, i, right--);
				} // 直到i不是maxColor
				
				if (nums[i] == minColor) {
					swap(nums, i, left++);
				}
			}
			
			minColor++;
			maxColor--; // 每一回合都把minColor和maxColor安顿好
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String args[]) {
		SortKColors sc = new SortKColors();
		int[] nums = new int[] { 2, 3, 4, 1, 2, 3, 4, 5, 7, 2, 5, 2, 6, 2, 7, 5, 4, 2, 3, 4, 5 };
		sc.sortKColors(nums, 7);
		for (int num : nums) {
			System.out.print(num + " ");
		}
	}
}
