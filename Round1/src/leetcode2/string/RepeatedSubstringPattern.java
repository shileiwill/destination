package leetcode2.string;
/**
 * 459. Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length();
        for (int subSize = len / 2; subSize >= 1; subSize--) { // block(repeatable substring) size
            if (len % subSize == 0) { // subsize must be a divisor of the whole string
                int blockCount = len / subSize;
                
                int cursor;
                String firstStr = str.substring(0, subSize);
                for (cursor = 1; cursor < blockCount; cursor++) {
                    if (!firstStr.equals(str.substring(cursor * subSize, cursor * subSize + subSize))) { 
                        // Make sure every substring is perfect fit
                        break;
                    }
                }
                
                if (cursor == blockCount) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
