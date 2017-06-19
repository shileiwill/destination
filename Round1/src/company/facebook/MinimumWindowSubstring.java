package company.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	public static void main(String[] args) {

	}

    public String minWindow(String s, String t) {
        int[] hash = new int[256];
        
        int count = t.length();
        for (char c : t.toCharArray()) {
            hash[c]++;
        }
        
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++; // Right is added here, so not included
            
            while (count == 0) {
                String newStr = s.substring(left, right);
                int newLen = right - left;
                
                if (newLen < len) {
                    len = newLen;
                    res = newStr;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
    
    /**
     * 438. Find all anagrams in a string
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

		Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
		
		The order of output does not matter.
		
		Example 1:
		
		Input:
		s: "cbaebabacd" p: "abc"
		
		Output:
		[0, 6]
		
		Explanation:
		The substring with start index = 0 is "cba", which is an anagram of "abc".
		The substring with start index = 6 is "bac", which is an anagram of "abc".
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (p.length() > s.length()) {
            return res;
        }
        
        int m = s.length();
        int n = p.length();
        int[] hash = new int[256];
        int count = n;
        
        for (int i = 0; i < n; i++) {
            hash[p.charAt(i)]++;
        }
        
        int left = 0, right = 0;
        while (right < m) {
            char now = s.charAt(right);
            
            if (hash[now] > 0) {
                count--;
            }
            hash[now]--;
            right++;
            
            if (count == 0) {
                res.add(left);
            }
            
            if (right - left == n) {
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
    
    
    /**
     * No duplicates uses set 
     * Duplicate use hash table 数出现的次数
     */
    public String minWindowNoDuplicate(String s, Set<Character> set) {
        int[] hash = new int[256];
        
        int count = set.size();
        for (char c : set) {
            hash[c]++;
        }
        
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++; // Right is added here, so not included
            
            while (count == 0) {
                String newStr = s.substring(left, right);
                int newLen = right - left;
                
                if (newLen < len) {
                    len = newLen;
                    res = newStr;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
}
