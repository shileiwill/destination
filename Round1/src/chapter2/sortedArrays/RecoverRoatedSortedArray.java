package chapter2.sortedArrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a rotated sorted array, recover it to sorted array in-place.

For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
Example
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 * @author Lei
 *
 */
public class RecoverRoatedSortedArray {
	
	public static void main(String[] args) {
		RecoverRoatedSortedArray r = new RecoverRoatedSortedArray();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(4);
		nums.add(5);
		nums.add(1);
		nums.add(2);
		nums.add(3);
//		{4, 5, 1, 2, 3}
		r.recoverRotatedSortedArray(nums);
	}
	// What if use no extra space 三步反转法
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // Find break index
        int pos = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                pos = i;
                break;
            }
        }
        
        reverse(nums, 0, pos); // inclusive
        reverse(nums, pos + 1, nums.size() - 1);
        reverse(nums, 0, nums.size() - 1);
    }
    
    private void reverse(ArrayList<Integer> nums, int start, int end) {
    	while (start < end) {
    		int temp = nums.get(start);
    		nums.set(start, nums.get(end));
    		nums.set(end, temp);
    		start++;
    		end--;
    	}
    }
	
    public void recoverRotatedSortedArray2(ArrayList<Integer> nums) {
        // Find break index
        int pos = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                pos = i;
                break;
            }
        }
        
        List<Integer> N = new ArrayList<Integer>(); 
        if (pos != -1) {
            for (int i = pos + 1; i < nums.size(); i++) {
                N.add(nums.get(i));
            }
            for (int i = 0; i <= pos; i++) {
                N.add(nums.get(i));
            }
            
            for (int i = 0; i < nums.size(); i++) {
                nums.set(i, N.get(i));
            }
        }
    }
}
