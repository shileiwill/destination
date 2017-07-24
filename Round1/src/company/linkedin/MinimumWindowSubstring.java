package company.linkedin;

/**
 * 76. Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

	public static void main(String[] args) {

	}

    public String minWindow(String s, String t) {
        int[] hash = new int[256];
        
        int count = t.length();
        for (char c : t.toCharArray()) {
            hash[c]++;
        }
        
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++; // Right is added here, so not included
            
            while (count == 0) {
                String newStr = s.substring(left, right);
                int newLen = right - left;
                
                if (newLen < len) {
                    len = newLen;
                    res = newStr;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
}
