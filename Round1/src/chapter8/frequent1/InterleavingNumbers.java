package chapter8.frequent1;

public class InterleavingNumbers {

	public static void main(String[] args) {
		InterleavingNumbers in = new InterleavingNumbers();
//		int[] nums = {-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18};
//		int[] nums = {-1, -2, -3, 4, 5};
		int[] nums = {-33,-19,-30,26,21};
		in.rerange(nums);
		
	}
	
    public void rerange(int[] nums) {
	    if (nums == null || nums.length == 0) {
	        return;
	    }
	    partition(nums, 0, nums.length - 1);
    }
    
    void partition(int[] nums, int left, int right) {
        
        // First sort negative and positive
        while (left < right) {
            while (left < right && nums[left] > 0) {
                left++;
            }
            
            while (left < right && nums[right] < 0) {
                right--;
            }
            
            swap(nums, left, right);
        }
        
        // Interleaving
        if (nums[right] > 0) {
            right++;
        }

        // Use an additional array to interleave numbers
        left = 0;
        int mid = right;
        int[] res = new int[nums.length];
        int index = 0;
        if (mid < nums.length / 2.0) {
        	// Right is bigger
        	boolean chooseRight = true;
        	while (left < mid && right < nums.length) {
        		res[index++] = chooseRight ? nums[right++] : nums[left++];
        		chooseRight = !chooseRight;
        	}
        	if (left < mid) {
        		res[index] = nums[left];
        	}
        	if (right < nums.length) {
        		res[index] = nums[right];
        	}
        } else {
        	// Left is equal or bigger
        	boolean chooseRight = false;
        	while (left < mid && right < nums.length) {
        		res[index++] = chooseRight ? nums[right++] : nums[left++];
        		chooseRight = !chooseRight;
        	}
        	if (left < mid) {
        		res[index] = nums[left];
        	}
        	if (right < nums.length) {
        		res[index] = nums[right];
        	}
        }
        
		for (int i = 0; i < res.length; i++) {
			nums[i] = res[i];
		}
    }
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
