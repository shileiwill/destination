package chapter2.sortedArrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 26. Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 * @author Lei
 *
 */
public class RemoveDuplicates {

	public static void main(String[] args) {

	}
	private int removeDuplicatesWithExtraSpace(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
		List<Integer> res = new ArrayList<Integer>();
		res.add(nums[0]);
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				res.add(nums[i]);
			}
		}
		
		for (int i = 0; i < res.size(); i++) {
			nums[i] = res.get(i);
		}
		
		return res.size();
	}
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        // i points to the unique elements
        int i = 0; // i is index
        
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] == nums[i]) {
                
            } else {
                i++; // j is unique, move i forward
                nums[i] = nums[j]; // And! put j there. After all these steps, i points to nums[j], which is the newest unique element
            }
        }
        
        return i + 1;
    }

}
