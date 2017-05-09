package company.linkedin;

public class LongestPalindrome {

	public static void main(String[] args) {
		LongestPalindrome lp = new LongestPalindrome();
		String s = "ccc";
		String res = lp.longestPalindrome(s);
		System.out.println(res);
	}


    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        int longest = 1;
        String res = s.substring(0, 1);
        
        for (int i = 1; i < s.length(); i++) {
            String now = extend(s, i, i);
            if (s.charAt(i) == s.charAt(i - 1)) {
                String second = extend(s, i - 1, i);
                if (second.length() > now.length()) {
                    now = second;
                }
            }
            
            if (now.length() > longest) {
                res = now;
                longest = now.length();
            }
        }
        
        return res;
    }
    
    String extend(String s, int left, int right) {
        left--;
        right++;
        while (left >= 0 && right < s.length()) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            
            if (l == r) {
                left--;
                right++;
            } else {
                break;
            }
        }
        
        return s.substring(left + 1, right);
    }

    // Longest Palindrome Sequence
    public int longestPalindromeSubseq2(String s) {
    	int[][] hash = new int[s.length()][s.length()];
    	return helper(s, hash, 0, s.length() - 1);
    }
    
    int helper(String s, int[][] hash, int left, int right) {
    	if (left > right) {
    		return 0;
    	}
    	
    	if (left == right) {
    		hash[left][right] = 1;
    		return 1;
    	}
    	
    	if (s.charAt(left) == s.charAt(right)) {
    		hash[left][right] = helper(s, hash, left + 1, right - 1) + 2; // 往里走
    	} else {
    		hash[left][right] = Math.max(helper(s, hash, left + 1, right),  helper(s, hash, left, right - 1));
    	}
    	
    	return hash[left][right];
    }
}
