package leetcode1.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 448. Find All Numbers Disappeared in an Array   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 4603
Total Submissions: 7232
Difficulty: Easy
Contributors: yuhaowang001
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */
public class FindAllNumbersDisappeared {
	// Use the principle of bucket sort. Take the advantage that values will always be 1 ≤ a[i] ≤ n
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            // Since nums[i] could be set to negative already, do an abs here
            int index = Math.abs(nums[i]) - 1; // Could be from 0 to nums.length - 1
            if (nums[index] > 0) {
                nums[index] = -nums[index]; // Once found, set to negative
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        
        return res;
    }
    
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        
        Arrays.sort(nums);
        int elem = 1;
        
        int i = 0;
        while (i < nums.length) {
            int num = nums[i];
            if (i != 0 && num == nums[i - 1]) {
                i++;
                continue;
            }
            
            if (num != elem) {
                res.add(elem);
            } else {
                i++;
            }
            elem++;
        }
        
        while (elem <= nums.length) {
            res.add(elem++);
        }
        
        return res;
    }
}
