package chapter5.dp;

public class PalindromePartitioning2 {

	public static void main(String[] args) {
		PalindromePartitioning2 p2 = new PalindromePartitioning2();
		int res = p2.minCut("mmd");
		System.out.println(res);
	}
	
    public int minCut(String s) {
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
