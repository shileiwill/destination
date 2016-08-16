package chapter5.dp;
/**
 * Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
 * @author Lei
 *
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String A, String B) {
        if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
            return 0;
        }
        int[][] hash = new int[A.length()][B.length()];
        
        // Initialization
        for (int i = 0; i < A.length(); i++) {
            hash[i][0] = (A.charAt(i) == B.charAt(0)) ? 1 : 0;
        }
        for (int i = 0; i < B.length(); i++) {
            hash[0][i] = (A.charAt(0) == B.charAt(i)) ? 1 : 0;
        }
        
        for (int i = 1; i < A.length(); i++) {
            for (int j = 1; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    hash[i][j] = hash[i - 1][j - 1] + 1;
                    // hash[i][j] = Math.max(hash[i - 1][j - 1] + 1, Math.max(hash[i - 1][j], hash[i][j - 1]));
                } else {
                    hash[i][j] = Math.max(hash[i - 1][j], hash[i][j - 1]);
                }    
            }
        }
        
        return hash[A.length() - 1][B.length() - 1];
    }
}
