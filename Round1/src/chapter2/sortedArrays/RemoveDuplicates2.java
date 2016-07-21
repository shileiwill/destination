package chapter2.sortedArrays;

import java.util.ArrayList;
import java.util.List;

/**
80. Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * @author Lei
 *
 */
public class RemoveDuplicates2 {

	public static void main(String[] args) {
		RemoveDuplicates2 r2 = new RemoveDuplicates2();
		int[] nums = {1, 1, 1, 2, 2, 3};
		
		r2.removeDuplicates(nums);
	}
	
	private int removeDuplicatesWithExtraSpace(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
		List<Integer> res = new ArrayList<Integer>();
		res.add(nums[0]);
		int pos = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				if (i - pos > 1) {
					// More than 2, Just skip
					continue;
				} else {
					// Add element but not increment pos
					res.add(nums[i]);
				}
			} else {
				// Find a new element, add and update pos
				res.add(nums[i]);
				pos = i;
			}
		}
		
		for (int i = 0; i < res.size(); i++) {
			nums[i] = res.get(i);
		}
		
		return res.size();
	}
	
    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        int i, j;
        int cur = 0;
        for (i = 0; i < nums.length;) {
        	// This 'now' is not necessary
            int now = nums[i];
            for (j = i; j < nums.length; j++) { // Go through all the same elements
                if (nums[j] != now) { // We can replace this 'now' with nums[i], as i is not changing.
                    break;
                }
                if (j - i < 2) {
                    nums[cur++] = nums[j];
                }
            }
            i = j;
        }
        
        return cur;
    }
    
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        boolean open = true;
        int size = 1;
        
        for (int i = 1; i < nums.length; i++) {
        	int now = nums[i];
        	
        	if (now == nums[i - 1] && open) { // Equals but amount <= 2, will add, but no more.
        		nums[size++] = now;
        		open = false;
        	} else if (now != nums[i - 1]) { // Not equal, will add for sure
        		nums[size++] = now;
        		open = true;
        	}
        }
        
        return size;
    }

}
