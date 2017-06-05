package company.uber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 291. Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
 */
public class WordPattern2 {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<String>();
        
        return helper(pattern, 0, str, 0, map, set);
    }
    
	boolean helper(String pattern, int i, String str, int j, Map<Character, String> map, Set<String> set) {
        // Base case
        if (i == pattern.length() && j == str.length()) {
            return true;
        }
        if (i == pattern.length() || j == str.length()) {
            return false;
        }
        
        char ch = pattern.charAt(i);
        
        // If this char is already in map, it will stop in this if statement
        if (map.containsKey(ch)) {
            String st = map.get(ch);
            
            if (!str.startsWith(st, j)) {
                return false;
            }
            
            return helper(pattern, i + 1, str, j + st.length(), map, set);
        }
        
        // This ch doesn't exist in map
        for (int k = j; k < str.length(); k++) {
            String sub = str.substring(j, k + 1);
            
            if (set.contains(sub)) {
                continue;
            }
            
            map.put(ch, sub);
            set.add(sub);
            if (helper(pattern, i + 1, str, k + 1, map, set)) { // Can't return directly
                return true;
            }
            map.remove(ch);
            set.remove(sub);
        }
        
        return false;
    }
}
