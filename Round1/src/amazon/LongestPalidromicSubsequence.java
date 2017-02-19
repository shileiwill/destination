package amazon;
/**
 * 516. Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 */
public class LongestPalidromicSubsequence {
    // Traditional DP not easy to understand. https://discuss.leetcode.com/topic/78603/straight-forward-java-dp-solution
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] hash = new int[len][len];
        
        return hash[0][len - 1];
    }
    
    public int longestPalindromeSubseq2(String s) {
        Integer[][] hash = new Integer[s.length()][s.length()];
        return helper(s, 0, s.length() - 1, hash);
    }
    // DFS with memorization
    int helper(String s, int left, int right, Integer[][] hash) {
        if (left > right) {
            return 0;
        }
        
        if (left == right) {
            return 1;
        }
        
        if (hash[left][right] != null) {
            return hash[left][right];
        }
        
        if (s.charAt(left) == s.charAt(right)) {
            hash[left][right] = helper(s, left + 1, right -1, hash) + 2;
        } else {
            hash[left][right] = Math.max(helper(s, left + 1, right, hash), helper(s, left, right - 1, hash));
        }
        
        return hash[left][right];
    }
}
