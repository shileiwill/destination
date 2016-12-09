package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        char[] charArr = pattern.toCharArray();
        String[] strArr = str.split(" ");
        
        if (charArr.length != strArr.length) {
            return false;
        }
        
        Map<Character, String> map1 = new HashMap<Character, String>();
        Map<String, Character> map2 = new HashMap<String, Character>();
        
        for (int i = 0; i < charArr.length; i++) {
            char ch = charArr[i];
            String st = strArr[i];
            
            if (map1.containsKey(ch)) {
                if (!map1.get(ch).equals(st)) {
                    return false;
                }
            } else if (map2.containsKey(st)) { // Has this value already
                if (!map2.get(st).equals(ch)) {
                    return false;
                }
            } else {
                map1.put(ch, st);
                map2.put(st, ch);
            }
        }
        
        return true;
    }
}
