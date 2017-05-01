package leetcode2.string;
/**
 * 5. Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
 */
public class LongestPalindromicSubstring {
    int left = 0, len = 0;
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            // Try 2 different scenarios, and compare all
            extendPalindrome(s, i, i); // Odd
            if (s.charAt(i) == s.charAt(i + 1)) {
                extendPalindrome(s, i, i + 1); // Even
            }
        }
        
        return s.substring(left, left + len);
    }
    
    void extendPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++; // Decrease and increase together
        }
        
        if (len < j - i - 1) {
            len = j - i - 1;
            left = i + 1;
        }
    }
    
    // Here is my version

    public String longestPalindromeHomeMade(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String single = extend(s, i, i);
            
            String doubleS = "";
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                doubleS = extend(s, i, i + 1);
            }
            
            String longer = doubleS.length() > single.length() ? doubleS : single;
            res = res.length() > longer.length() ? res : longer;
        }
        
        return res;
    }
    
    String extend(String s, int left, int right) {
        left--;
        right++;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                return s.substring(left + 1, right);
            }
        }
        
        return s.substring(left + 1, right);
    }

}
