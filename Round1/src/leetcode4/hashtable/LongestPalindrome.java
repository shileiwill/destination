package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;
/**
 * 409. Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (char c : ch) {
            map.put(c, map.getOrDefault(c, 0) + 1);    
        }
        
        boolean hasSingle = false;
        int count = 0;
        for (int v : map.values()) {
            int remainder = v % 2;
            if (!hasSingle && remainder == 1) {
                hasSingle = true;
            }
            
            int EvenCount = v - remainder;
            count += EvenCount;
        }
        
        if (hasSingle) {
            count++;
        }
        
        return count;
    }
}
