package chapter5.dp;
/**
 * 97. Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
 * @author Lei
 *
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // hash[i][j] 表示S1的前i个字符与S2的前j个字符能否组成S3的前i+j个字符
        boolean[][] hash = new boolean[s1.length() + 1][s2.length() + 1];
        hash[0][0] = true;
        
        for (int i = 1; i <= s1.length(); i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1) && hash[i - 1][0]) {
                hash[i][0] = true;
            }
        }
        
        for (int i = 1; i <= s2.length(); i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1) && hash[0][i - 1]) {
                hash[0][i] = true;
            }
        }
        
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if ((s1.charAt(i - 1) == s3.charAt(i + j - 1) && hash[i - 1][j]) ||
                    (s2.charAt(j - 1) == s3.charAt(i + j - 1) && hash[i][j - 1])) {
                    hash[i][j] = true;
                }
            }
        }
        
        return hash[s1.length()][s2.length()];
    }
}
