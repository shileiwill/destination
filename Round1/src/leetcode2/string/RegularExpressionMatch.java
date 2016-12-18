package leetcode2.string;
/**
 * 10. Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatch {
    /*
    1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	3, If p.charAt(j) == '*': 
   	here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        boolean[][] state = new boolean[s.length() + 1][p.length() + 1];
        state[0][0] = true;
        
        // No need to initialize state[i][0], by default they are false
        // for (int i = 1; i < state[0].length; i++) {
        //     if (state[0][i - 1] && p.charAt(i - 1) == '*') {
        //         state[0][i] = true;
        //     }
        // }
        for (int j = 1; j < state[0].length; j++) {
            if (p.charAt(j - 1) == '*') {
                if (state[0][j - 1] || (j > 1 && state[0][j - 2])) { // [j - 2] 可以让前边的一个出现0次
                    state[0][j] = true;
                }
            } 
        }
        
        for (int i = 1; i < state.length; i++) {
            for (int j = 1; j < state[i].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    state[i][j] = state[i - 1][j - 1];
                }
                
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        state[i][j] = state[i][j - 2]; // * can only stand for 0
                    } else {
                        // * can stand for 0, 1, and multiple
                        state[i][j] = state[i][j - 2] || state[i][j - 1] || state[i - 1][j];
                    }
                }
            }
        }
        
        return state[s.length()][p.length()];
    }
}