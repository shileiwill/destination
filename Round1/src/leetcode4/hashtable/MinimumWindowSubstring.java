package leetcode4.hashtable;
/**
 * 76. Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int left = 0, right = 0, count = t.length();
        int start = 0, len = Integer.MAX_VALUE;
        int[] hash = new int[256];
        
        for (char c : t.toCharArray()) {
            hash[c]++;
        }
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++;
            
            while (count == 0) { // As long as all chars in T are covered, shorten from left
                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] == 0) { // This character is useful
                    count++;                    
                }
                
                hash[leftChar]++; // Need another "this left char"
                left++; // Move Left forward
            }
        }
        
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
