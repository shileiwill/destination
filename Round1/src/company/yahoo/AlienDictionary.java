package company.yahoo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
					
					if (!set.contains(c2)) {
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
