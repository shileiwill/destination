package company.facebook;
// 重要
public class RemoveDuplicates {

	public static void main(String[] args) {
		RemoveDuplicates rd = new RemoveDuplicates();
		int[] arr = {1, 3, 3, 3, 4, 4, 5, 9, 9, 10};
		rd.removeDuplicates1(arr);
		arr = new int[]{1, 3, 3, 3, 4, 4, 4, 4, 4, 5, 9, 9, 10};
		rd.removeDuplicates2(arr);
		arr = new int[]{1, 3, 3, 3, 4, 4, 4, 4, 5, 9, 9, 10, 10};
		rd.removeDuplicates3(arr);
		arr = new int[]{1, 3, 3, -3, 4, -4, -4, 4, 5, -9, 9, -10, 10};
		rd.moveNegative(arr);
		
		rd.moveNegativeMyStyle(arr);
	}
	// 26. Leave one in final array
    public int removeDuplicates1(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        int size = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                nums[size++] = nums[i]; // Dont change nums[i] 
            }
        }
        
        print(nums, size);
        return size;
    }
    
    // 80. Duplicates are allowed at most 2
    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        boolean open = true;
        int size = 1;
        
        for (int i = 1; i < nums.length; i++) {
        	int now = nums[i];
        	
        	if (now == nums[i - 1]) { // Equals but amount <= 2, will add, but no more.
        		if (open) {
            		nums[size++] = now;
            		open = false;
        		}
        	} else { // Not equal, will add for sure
        		nums[size++] = now;
        		open = true;
        	}
        }

        print(nums, size);
        return size;
    }
    
    // No duplicates allowed at all
    public int removeDuplicates3(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        int size = 0;
        int prevIndex = 0; // The first appearance of this value
        
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] == nums[prevIndex]) {
        		continue;
        	} else {
        		if (i == prevIndex + 1) { // This means prevValue appears only once
        			nums[size++] = nums[prevIndex];
        		}
        		prevIndex = i;
        	}
        }
        
        if (prevIndex == nums.length - 1) {
        	nums[size++] = nums[prevIndex];
        }
        
        print(nums, size);
        return size;
    }
    
    // Move all negative numbers to left, and keep original order
    void moveNegative(int[] arr) {
    	// Use the idea of insertion sort
    	for (int i = 1; i < arr.length; i++) {
    		if (arr[i] < 0) {
    			int cur = i - 1;
    			while (cur >= 0 && arr[cur] > 0) {
    				// swap(cur, cur + 1)
    				int tmp = arr[cur + 1];
    				arr[cur + 1] = arr[cur];
    				arr[cur] = tmp;
    				
    				cur--;
    			}
    		}
    		// If arr[i] is positive, no need to do anything
    	}
    	
    	print(arr, arr.length);
    }
    
    void moveNegativeMyStyle(int[] arr) {
    	int left = 0, right = 0;
    	while (left < arr.length && right < arr.length) {
    		while (left < arr.length && arr[left] < 0) {
    			left++;
    		}
    		right = Math.max(left + 1, right);
    		while (right < arr.length && arr[right] >= 0) {
    			right++;
    		}
    		
    		if (right < arr.length) {
    			swap(arr, left, right);
    		}
    		
    		left++;
    		right++;
    	}
    	
    	print(arr, arr.length);
    }
    
    private void swap(int[] arr, int left, int right) {
		int tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
		
	}
	void print(int[] arr, int size) {
    	for (int i = 0; i < size; i++) {
    		System.out.print(arr[i] + "==");
    	}
    	
    	System.out.println();
    }
}
