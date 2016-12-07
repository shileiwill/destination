package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

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
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        
        int left = 0, right = 0, count = p.length();
        int[] hash = new int[256];
        
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) { // We need this character
                count--;
            }
            
            hash[rightChar]--;
            right++;
            
            if (count == 0) { // All found
                res.add(left);
            }
            
            if (right - left == p.length()) { // Long enough, can't go farther
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) { // P has this left character, and it contributed to the result
                    count++; // Need to find one more this left character
                }
                
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
}
