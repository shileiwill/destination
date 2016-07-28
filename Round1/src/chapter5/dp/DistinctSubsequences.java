package chapter5.dp;
/**
 * 115. Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
 * @author Lei
 *
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        // End with the last char
    	// hash[i][j]表示S的前i个字符中选取T的前j个字符，有多少种方案
        int[][] hash = new int[s.length() + 1][t.length() + 1];
        
        for (int i = 0; i <= s.length(); i++) {
            hash[i][0] = 1; // Must be distinct
        }
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) { // If they are the same, we can either use it or not
                    hash[i][j] = hash[i - 1][j] + hash[i - 1][j - 1]; // We are looking for different solutions
                } else {
                	hash[i][j] = hash[i - 1][j]; // We can always delete the char from S
                }
            }
        }
        
        return hash[s.length()][t.length()];
    }
}
