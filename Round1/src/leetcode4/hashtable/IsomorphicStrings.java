package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<Character, Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);
            
            if (map1.containsKey(s1)) {
                if (!map1.get(s1).equals(t1)) {
                    return false;
                }
            } else if (map2.containsKey(t1)) {
                if (!map2.get(t1).equals(s1)) {
                    return false;
                }
            } else {
                map1.put(s1, t1);
                map2.put(t1, s1);
            }
        }
        
        return true;
    }
}
