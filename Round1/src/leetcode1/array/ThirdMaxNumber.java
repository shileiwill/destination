package leetcode1.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/**
 * 414. Third Maximum Number
 * 
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaxNumber {
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE; // largest
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE; // smallest
        
        for (int num : nums) {
            if (num == max1 || num == max2 || num == max3) {
                continue;
            }
            
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }
        
        if (max3 == Long.MIN_VALUE) {
            return (int)max1;
        }
        
        return (int)max3;
    }
    
    public int thirdMax2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        Arrays.sort(nums);
        
        int prev = Integer.MAX_VALUE;
        int count = 0;
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != prev && count <= 3) {
                prev = nums[i];
                count++;
            }
            
            if (count == 3) { // Found
                return nums[i];
            }
        }
        
        // Not Found
        return nums[nums.length - 1];
    }
    
    public int thirdMax3(int[] nums) {
        // PriorityQueue, by default, is min-heap, which means, poll will get the min
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                set.add(num);
                heap.offer(num);
                if (heap.size() > 3) {
                    heap.poll(); // Throw the min
                }
            }
        }
        
        if (heap.size() <= 2) { // If less than 3 items, return the largest
            while (heap.size() > 1) {
                heap.poll();
            }
        }
        
        return heap.peek(); // Return the top, could be the only one left, or the third maximum
    }
}
