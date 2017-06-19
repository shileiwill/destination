package leetcode12.heap;

import java.util.Comparator;
import java.util.LinkedList;
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
    public int[] maxSlidingWindowHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[]{};
        }
        
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            public int compare(Integer val1, Integer val2) {
                return val2 - val1;
            }
        });
        
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        res[index++] = heap.peek();
        
        for (int i = k; i < nums.length; i++) {
            int old = nums[i - k];
            heap.remove(old);
            
            heap.offer(nums[i]);
            
            res[index++] = heap.peek();
        }
        
        return res;
    }
    
    // O(N)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[]{};
        }
        
        int len = nums.length;
        int[] res = new int[len - k + 1];
        LinkedList<Integer> list = new LinkedList<Integer>(); // 需要两头同时操作，所以LinkedList
        
        for (int i = 0; i < nums.length; i++) {
            if (!list.isEmpty() && list.peekFirst() < i - k + 1) {
                list.pollFirst();
            }
            
            while (!list.isEmpty() && nums[i] > nums[list.peekLast()]) {
                list.pollLast();
            }
            
            list.addLast(i);
            
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[list.peekFirst()];
            }
        }
        
        return res;
    }
}
