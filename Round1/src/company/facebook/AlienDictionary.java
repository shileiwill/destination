package company.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 269. There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

 * 给一个外星人字典，所有单词按外星人的alphabet排序。求出外星人的alphabet顺序。想了一会儿，觉得是个topology sort问题
 */
public class AlienDictionary {

	public static void main(String[] args) {

	}

	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>(); // 哪些char依赖这个char
		Map<Character, Integer> degree = new HashMap<Character, Integer>(); // 这个char依赖多少个char
		
		for (String str : words) {
			for (char c : str.toCharArray()) {
				degree.put(c, 0);
			}
		}
		
		for (int i = 0; i < words.length - 1; i++) {
			String now = words[i];
			String next = words[i + 1];
			
			int len = Math.min(now.length(), next.length());
			for (int j = 0; j < len; j++) {
				char c1 = now.charAt(j);
				char c2 = next.charAt(j);
				
				if (c1 == c2) {
					if (j < now.length() - 1 && j == next.length() - 1) {
						return "";
					}
				} else {
					Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
					
					if (!set.contains(c2)) { // Add only if set doesnt have this character
						set.add(c2);
						map.put(c1, set);
						degree.put(c2, degree.get(c2) + 1);
					} 
					break;
				}
			}
		}
		
		// BFS
		Queue<Character> queue = new LinkedList<Character>();
		for (Map.Entry<Character, Integer> entry : degree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			char now = queue.poll();
			sb.append(now);
			
			for (char ch : map.get(now)) {
				degree.put(ch, degree.get(ch) - 1);
				
				if (degree.get(ch) == 0) {
					queue.offer(ch);
				}
			}
		}
		
		if (degree.size() != sb.length()) {
			return "";
		}
		
		return sb.toString();
	}
}
