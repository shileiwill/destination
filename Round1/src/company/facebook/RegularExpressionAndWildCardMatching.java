package company.facebook;

public class RegularExpressionAndWildCardMatching {

	public static void main(String[] args) {
		RegularExpressionAndWildCardMatching re = new RegularExpressionAndWildCardMatching();
		boolean res = re.matchByDPBetter("aa", "*");
		System.out.println(res);
	}

	// 给两个字符串，pattern里边可能有*, ?. * match 前边任意数量的字符， ? match 一个
	/**
	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	
	The matching should cover the entire input string (not partial).
	
	The function prototype should be:
	bool isMatch(const char *s, const char *p)
	
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
	 */
	boolean matchByDP(String s, String p) {
		int m = s.length();
		int n = p.length();
		
		boolean[][] hash = new boolean[m + 1][n + 1];
		hash[0][0] = true;
		
		for (int i = 1; i <= n; i++) {
			hash[0][i] = hash[0][i - 1] && p.charAt(i - 1) == '*';
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					hash[i][j] = hash[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					int cur = i;
					while (cur >= 0) { // Changes are made here
						if (hash[cur][j - 1]) {
							hash[i][j] = true;
							break;
						}
						cur--;
					}
				}
			}
		}
		
		return hash[m][n];
	}
	
	// 把string 和 pattern 反过来
	boolean matchByDPBetter(String s, String p) {
		int n = p.length();
		int m = s.length();
		
		boolean[][] hash = new boolean[n + 1][m + 1]; // pattern, string
		hash[0][0] = true;
		
		for (int i = 1; i <= n; i++) {
			hash[i][0] = hash[i - 1][0] && p.charAt(i - 1) == '*';
		}
		
		for (int i = 1; i <= n; i++) { // Pattern first
		    boolean flag = false;
		    
		    for (int j = 1; j <= m; j++) { // Each char in String
			    flag = flag || hash[i - 1][j]; // As long as 1 char is true, flag is true
				if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '?') {
					hash[i][j] = hash[i - 1][j - 1];
				} else if (p.charAt(i - 1) == '*') {
				    hash[i][j] = (i == 1) || flag; // (i == 1) Pattern中第一个就是*， 这个可以match无限个char
				}
			}
		}
		
		return hash[n][m];
	}
	
	/**
		10. Implement regular expression matching with support for '.' and '*'.
	
		'.' Matches any single character.
		'*' Matches zero or more of the preceding element. 必须是前边的element.
	
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
		
	    1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
		2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
		3, If p.charAt(j) == '*': 
	   	here are two sub conditions:
	               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
	               2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
	                              dp[i][j] = dp[i-1][j]    // in this case, a* counts as multiple a 
	                           or dp[i][j] = dp[i][j-1]    // in this case, a* counts as single a
	                           or dp[i][j] = dp[i][j-2]    // in this case, a* counts as empty
	    */
	    public boolean isMatch(String s, String p) {
	        if (s == null || p == null) {
	            return false;
	        }
	        
	        boolean[][] state = new boolean[s.length() + 1][p.length() + 1];
	        state[0][0] = true;
	        
	        for (int j = 1; j <= p.length(); j++) {
	            if (p.charAt(j - 1) == '*') {
	                if (state[0][j - 1] || (j >= 2 && state[0][j - 2])) { // [j - 2] 可以让前边的一个出现0次
	                    state[0][j] = true;
	                }
	            } 
	        }
	        
	        for (int i = 1; i <= s.length(); i++) {
	            for (int j = 1; j <= p.length(); j++) {
	                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
	                    state[i][j] = state[i - 1][j - 1];
	                } else if (p.charAt(j - 1) == '*') {
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
