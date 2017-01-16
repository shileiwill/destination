package leetcode12.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 239. Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
 */
public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // By default, heap is minHeap, which will return minimum value
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        
        if (nums.length == 0) {
            return new int[0];
        }
        
        // Fill the first k numbers
        int i = 0, m = k;
        while (i < nums.length && m > 0) {
            heap.offer(nums[i]);
            m--;
            i++;
        }
        
        // If no more candidates, just return
        if (i == nums.length) {
            res[0] = heap.peek();
            return res;
        }
        
        // Step one by one
        int prev = nums[0];
        for (i = 1; i <= nums.length - k; i++) {
            res[i - 1] = heap.peek(); // Add to result
            heap.remove(Integer.valueOf(prev)); // Remove the first element in heap
            prev = nums[i]; // Update the first element
            heap.offer(nums[i + k - 1]); // Add next following element to heap
        }
        
        // Don't forget the last element
        res[i - 1] = heap.peek();
        
        return res;
    }
    
    public static void main(String[] args) {
    	int[] nums = {1,3,-1,-3,5,3,6,7};
    	int[] res = maxSlidingWindow(nums, 3);
    	
    	for (int val : res) {
    		System.out.println(val);
    	}
	}
}
