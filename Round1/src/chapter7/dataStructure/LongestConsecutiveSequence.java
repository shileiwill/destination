package chapter7.dataStructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * 128. Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * @author Lei
 *
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] nums = {1, 3, 2, 2, 4, 34, 35, 2, 23};
		int[] nums2 = {1 , -1, 0};
	}
	// Brute force
    public int longestConsecutiveBrute(int[] nums) {
        int count = 1;
        Arrays.sort(nums);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
                max = Math.max(max, count);
            } else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                count = 1;
            }
        }
        
        return max;
    }
    
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        int max = 0;
        for (int num : nums) {
            
            int down = num - 1; // Going down
            while (set.contains(down)) {
                set.remove(down); // This will speed up
                down--;
            }
            
            int up = num + 1; // Going up
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            
            max = Math.max(max, up - down -1);
        }
        
        return max;
    }
    
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        int max = Integer.MIN_VALUE;
        
        // How to use set to iterator. 
        while (!set.isEmpty()) {
            int origin = set.iterator().next(); // We cant use get() in Set. but this is how to get an element
            int num = origin;
            
            int count = 1;
            set.remove(num);
            
            while (set.contains(--num)) {
                count++;
                set.remove(num);
            }
            
            while (set.contains(++origin)) {
                count++;
                set.remove(origin);
            }
            
            max = Math.max(max, count);
        }
        
        // Here is iterating array
//        for (int num : nums) {
//            if (!set.contains(num)) {
//                continue;
//            }
//            
//            int count = 1;
//            set.remove(num);
//            
//            int origin = num; // Keep a note of original state
//            
//            while (set.contains(--num)) {
//                count++;
//                set.remove(num);
//            }
//            
//            while (set.contains(++origin)) {
//                count++;
//                set.remove(origin);
//            }
//            
//            max = Math.max(max, count);
//        }
        
        return max;
    }
}
