package company.yahoo;

public class MoveZeros {

	public static void main(String[] args) {
		MoveZeros mz = new MoveZeros();
		int[] nums = {0, 1, 0, 3, 0, 2, 7, 0, 12};
		
		mz.moveZeroes2(nums);
		
		for (int val : nums) {
			System.out.print(val + "--");
		}
	}

	// nums = [0, 1, 0, 3, 12]
	public void moveZeroes2(int[] nums) {
		int left = 0;
		int right = 1;
		int len = nums.length;
		
		while (right < len) {
			while (left < len && nums[left] != 0) {
				left++;
			} // Left is the first 0
			
			if (left < len) {
				right = left + 1;
				while (right < len && nums[right] == 0) {
					right++;
				} // Right is the first non-0
				
				if (right < len) {
					swap(nums, left, right);
				}
			}
			
			left++;
			right++;
		}
	}
	
	void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
}
