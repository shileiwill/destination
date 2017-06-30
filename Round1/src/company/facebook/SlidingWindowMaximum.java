package company.facebook;
//重要
// 给一个array，还有一个windows size。 滑动window，求最大。 follow up，一样的参数，要求是从array里选3个windows，求所有windows的item sum最大，window 不能相互重合
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
/**
 * 239. Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.

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
        int index = 0; // 单独用一个index计数 方便省事儿
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            public int compare(Integer val1, Integer val2) {
                return val2 - val1;
            }
        });
        
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        res[index++] = heap.peek(); // 在外边就添加进去
        
        for (int i = k; i < nums.length; i++) { // N * (K + log(K))
            int old = nums[i - k];
            heap.remove(old);
            
            heap.offer(nums[i]);
            
            res[index++] = heap.peek();
        }
        
        return res;
    }
    
    // O(N) 的方法很好，得记住
    // O(N) 有点像histogram， trapping rain water 维护一个单调递减数列
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[]{};
        }
        
        int len = nums.length;
        int[] res = new int[len - k + 1];
        LinkedList<Integer> list = new LinkedList<Integer>(); // 需要两头同时操作，所以LinkedList 实际上是一个双向链表
        
        for (int i = 0; i < nums.length; i++) { // List存index
            if (!list.isEmpty() && list.peekFirst() < i - k + 1) { // 最左边的过时了
                list.pollFirst();
            }
            
            // 我们只关心在一个section中的max, 如果左边的值小于新加入的值，左边的小值就没价值了，直接扔掉，这样保证List最左边就总是下一个max
            while (!list.isEmpty() && nums[i] > nums[list.peekLast()]) { // 只留下大数，小数不管用 最终是个递减数列
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
