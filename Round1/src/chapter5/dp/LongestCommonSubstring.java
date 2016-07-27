package chapter5.dp;
/**
 * Given two strings, find the longest common substring.

Return the length of it.

 Notice

The characters in substring should occur continuously in original string. This is different with subsequence.

Have you met this question in a real interview? Yes
Example
Given A = "ABCD", B = "CBCE", return 2.
 * @author Lei
 *
 */
public class LongestCommonSubstring {
    public int longestCommonSubstring(String A, String B) {
        // To deal with case:  one is empty
        if (A.length() == 0 || B.length() == 0) {
            return 0;
        }
        
        // 以i, j结尾的字串的公共子串的长度
        int[][] hash = new int[A.length()][B.length()];
        
        for (int i = 0; i < A.length(); i++) {
            hash[i][0] = A.charAt(i) == B.charAt(0) ? 1 : 0;
        }
        
        for (int i = 0; i < B.length(); i++) {
            hash[0][i] = A.charAt(0) == B.charAt(i) ? 1 : 0;
        }
        
        for (int i = 1; i < A.length(); i++) {
            for (int j = 1; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    hash[i][j] = hash[i - 1][j - 1] + 1;
                } else {
                    hash[i][j] = 0;
                }
            }    
        }
        
        int max = 0;
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                max = Math.max(max, hash[i][j]);
            }
        }
        
        return max;
    }
}
