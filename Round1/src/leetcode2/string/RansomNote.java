package leetcode2.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */
public class RansomNote {
    public boolean canConstruct2(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
                
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }
        
        return true;
    }
    
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] hash = new int[26];
        
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            hash[c - 'a']++;
        }
        
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            hash[c - 'a']--;
            
            if (hash[c - 'a'] < 0) {
                return false;
            }
        }
        
        return true;
    }
}
