package company.facebook;
/**
 * 283. Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeroes {
	
	public static void main(String[] args) {
		MoveZeroes mz = new MoveZeroes();
		int[] nums = {0, 1, 0, 5, 3, 12};
		mz.moveZeroesNoOrderMoreMoves(nums);
		
		for (int val : nums) {
			System.out.print(val + "---");
		}
	}
	
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
    
    // If I dont care about the order, just move non-0 to left
    public void moveZeroesNoOrder(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0) {
            	left++;
            }
            
            while (left < right && nums[right] == 0) {
            	right--;
            }
            
            if (left < right) {
            	swap(nums, left, right);
            }
            
            left++;
            right--;
        }
        
        if (nums[left] == 0) {
        	System.out.println("here");
        	System.out.println(left - 1);
        } else {
        	System.out.println(left);
        }
    }
    
    // You can also do this, but not as good as above. This requires more move().
    // However, this requires only 1 loop
    /**
     *  第一题也是 move zeros， 我一上来就用的双指针的做法， 复杂度是o(n)， 但是我用的两个 while loop， 然后他说不行要改， 
     *  最后改成了一个loop，但本质是一样的。 然后followup 就是用的收尾指针 swap
     * @param nums
     */
    public void moveZeroesNoOrderMoreMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] != 0) {
        		nums[size++] = nums[i]; // I dont care about anything after non-0
        	}
        }
        
        System.out.println(size);
    }
    
    // Use insert index. 这个的write比上边的方案多
    public void moveZeroesUsingInsertIndex(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] != 0) { // 非零，往左边插
        		nums[insertPos++] = nums[i]; 
        	}
        }
        
        while (insertPos < nums.length) {
        	nums[insertPos++] = 0;
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
