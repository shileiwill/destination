package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;
/**
 * 266. Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int single = 0;
        
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (int v : map.values()) {
            int remainder = v % 2;
            
            if (remainder == 1) {
                single++;
            }
            
            if (single >= 2) {
                return false;
            }
        }
        
        return true;
    }
}
