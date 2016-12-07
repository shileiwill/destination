package leetcode4.hashtable;
/**
 * 3. Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class ValidSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, count = 0; // The count of duplicates
        int len = 0;
        int[] hash = new int[256];
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) { // This right character exists already
                count++;
            }
            // Without repeating
            hash[rightChar]++;
            right++;
            
            while (count > 0) { // As long as there is duplicate, need to move left forward
                char leftChar = s.charAt(left);
                if (hash[leftChar] > 1) {
                    count--;
                }
                hash[leftChar]--;
                left++;
            }
            
            len = Math.max(len, right - left);
        }
        
        return len;
    }
}
