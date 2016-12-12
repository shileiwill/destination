package leetcode4.hashtable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 358. Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
 */
public class RearrangeString {
	/**
	 * 
	 * The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue). 
	 * PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.
	 * In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap. 
	 * The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.
	 */
    public String rearrangeString(String str, int k) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        // Build Map
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Customized max heap
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(
            new Comparator<Map.Entry<Character, Integer>>() {
                public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                    return e2.getValue() - e1.getValue();
                }
        });
        
        // Put all the Map entries to this Heap, sort!
        heap.addAll(map.entrySet());
        
        // A waiting list, first in first out
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<Map.Entry<Character, Integer>>();
        
        // Start Greedy Calculation
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            sb.append(entry.getKey()); // Add to result
            entry.setValue(entry.getValue() - 1); // Reduce the count
            queue.offer(entry); // Add to waiting list
            
            if (queue.size() < k) { // No need to remove from waiting list yet
                continue;
            }
            
            // Need to remove from waiting list, and add back to heap
            Map.Entry<Character, Integer> front = queue.poll();
            if (front.getValue() != 0) {
                heap.offer(front);
            }
        }
        
        // If rearrangement is impossible, some characters are left in waiting list
        return (sb.length() == str.length()) ? sb.toString() : "";
    }
}
