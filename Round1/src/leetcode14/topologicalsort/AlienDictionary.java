package leetcode14.topologicalsort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 269. There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    // https://discuss.leetcode.com/topic/28308/java-ac-solution-using-bfs/34
    public String alienOrder(String[] words) {
        String res = "";
        if (words == null || words.length == 0) {
            return res;
        }
        
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>(); // The value is how many chars are before current char
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree.put(c, 0); // degree also stands for the count of different chars
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            
            int len = Math.min(cur.length(), next.length());
            
            for (int j = 0; j < len; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
                    if (!set.contains(c2)) { // c2 is new to c1
                        set.add(c2);
                        map.put(c1, set); // This line is required. The set could be a new set
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break; // Found the first different char, no need to go further
                } else {
                    // To deal with : "wrt" must appears before "wrtkj"
                	if(j < cur.length() - 1 && j == next.length() - 1) {
                        return "";
                    }
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
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res += c;
            
            if (map.containsKey(c)) {
                for (char cc : map.get(c)) {
                    degree.put(cc, degree.get(cc) - 1);
                    if (degree.get(cc) == 0) {
                        queue.offer(cc);
                    }
                }
            }
        }
        
        if (degree.size() != res.length()) {
            return "";
        }
        return res;
    }
}
