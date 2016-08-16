package chapter5.dp;

public class PalindromePartitioning2 {

	public static void main(String[] args) {
		PalindromePartitioning2 p2 = new PalindromePartitioning2();
		int res = p2.minCut5("mmd");
		System.out.println(res);
	}
	
	// My version
    public int minCut5(String s) {
        if (isPalindrome(s)) {
            return 0;
        }
        int[] hash = new int[s.length()];  
        // 前i个字符, including i, 组成的字符串需要至少多少次cut
        for (int i = 0; i < s.length(); i++) { // Index is very tricky
            hash[i] = i; // Worst case is cut everywhere
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1); // j + 1 to i
                if (isPalindrome(sub)) {
                    hash[i] = Math.min((j == 0 ? 0 : hash[j - 1]) + 1, hash[i]);
                }
            }
        }
        
        return hash[s.length() - 1];
    }
    
    private boolean[][] getPalindrome(String s) {
        boolean[][] res = new boolean[s.length()][s.length()];
        
        // Only one letter (length == 1)
        for (int i = 0; i < s.length(); i++) {
            res[i][i] = true;
        }
        
        // Two letters (length == 2)
        for (int i = 0; i < s.length() - 1; i++) {
            res[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        // Len and Start
        // Dynamic length, Great to use start point and length! Easy understanding
        for (int len = 2; len < s.length(); len++) {
            for (int start = 0; start + len < s.length(); start++) {
                res[start][start + len] = res[start + 1][start + len - 1] &&
                                        (s.charAt(start) == s.charAt(start + len));
            }
        }
        
        return res;
    }
    
    public int minCut(String s) {
        int[] hash = new int[s.length() + 1];  
        boolean[][] isPalindromeBoard = getPalindrome(s);
        
        // 前i个字符组成的字符串需要至少多少次cut
        for (int i = 1; i <= s.length(); i++) {
            hash[i] = i; // Worst case is cut everywhere. This includes a cut at the end
            for (int j = 0; j < i; j++) {
                if (isPalindromeBoard[j][i - 1]) {
                    hash[i] = Math.min(hash[j] + 1, hash[i]);
                }
            }
        }
        
        return hash[s.length()] - 1;
    }
    
    public int minCut2(String s) {
        if (isPalindrome(s)) {
            return 0;
        }
        int[] hash = new int[s.length() + 1];  
        // 前i个字符组成的字符串需要至少多少次cut
        for (int i = 0; i <= s.length(); i++) { // Index is very tricky
            hash[i] = i - 1; // Worst case is cut everywhere
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i); // j + 1 to i
                if (isPalindrome(sub)) {
                    hash[i] = Math.min(hash[j] + 1, hash[i]);
                }
            }
        }
        
        return hash[s.length()];
    }
    
    private boolean isPalindrome(String s) {
        int len = s.length();
        if (len == 1) {
            return true;
        }
        
        int i = 0;
        int j = len - 1;
        
        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            if (left != right) {
                return false;
            }
            i++;
            j--;
        }
        
        return true;
    }

}
