package company.uber;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

/**
 * 358. Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
 */
public class RearrangeStringKDistanceApart {

	public static void main(String[] args) {

	}

	// leetcode 358 Rearrange String k Distance Apart 简化版，相当于是只考虑k=2的情况
	public String rearrangeString(String str, int k) {
		StringBuilder sb = new StringBuilder();
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (char c : str.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(map.size(), new Comparator<Map.Entry<Character, Integer>>(){
			@Override
			public int compare(Entry<Character, Integer> entry1, Entry<Character, Integer> entry2) {
				return entry2.getValue() - entry1.getValue();
			}
		});
		
		heap.addAll(map.entrySet());
		
		Queue<Map.Entry<Character, Integer>> queue = new LinkedList<Map.Entry<Character, Integer>>();
		
		while (!heap.isEmpty()) {
			Map.Entry<Character, Integer> now = heap.poll(); // Current largest occurance
			sb.append(now.getKey());
			
			now.setValue(now.getValue() - 1);
			queue.offer(now);
			
			if (queue.size() < k) {
				continue;
			}
			
			Map.Entry<Character, Integer> entry = queue.poll();
			if (entry.getValue() != 0) {
				heap.offer(entry);
			}
		}
		
		return sb.length() == str.length() ? sb.toString() : "";
	}
}
