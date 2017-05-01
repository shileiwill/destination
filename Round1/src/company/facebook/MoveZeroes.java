package company.facebook;
/**
 * 283. Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeroes {
	
	// By myself!!!
    public void moveZeroesBest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0, right = 0;
        while (right < nums.length) {
            while (left < nums.length && nums[left] != 0) {
                left++; // left will be the first one which is 0
            }
            
            right = Math.max(right, left + 1);
            while (right < nums.length && nums[right] == 0) {
                right++; // Right will be the first non-0 after left
            }
            
            if (right < nums.length) {
                swap(nums, left, right);
            }
            
            left++;
            right++;
        }
    }
    
	// left指向左边的0， right指向left之后的第一个非零
    public void moveZeroes(int[] nums) {
        for (int left = 0; left < nums.length; left++) {
            if (nums[left] != 0) {
                continue;
            }
            
            for (int right = left + 1; right < nums.length; right++) {
                if (nums[right] == 0) {
                    continue;
                } else {
                    // At this point, left is 0, right is non-0
                    swap(nums, left, right);
                    break;
                }
            }
        }
    }
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    
   public void moveZeroesBetter(int[] N) {
        for (int left = 0; left < N.length; left++) {
                while (left < N.length && N[left] != 0) {
                    left++;
                }
                
                int right = left + 1;
                while (right < N.length && N[right] == 0) {
                    right++;
                }
                
                if (left < N.length && right < N.length) {
                    swap(N, left, right);
                }
        }
    }
}
