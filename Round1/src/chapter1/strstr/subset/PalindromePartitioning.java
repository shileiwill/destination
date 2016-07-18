package chapter1.strstr.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
 * @author Lei
 *
 */
public class PalindromePartitioning {

	public static void main(String[] args) {
		String a = "abcdefg";
		// Include start, but not the end. The maximum number for the end is the length of string, no larger.
		String str = a.substring(1, 7);
		
		System.out.println(str);
	}
	
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        
        if (s == null) {
            return null;
        }
        
        helper(res, list, s, 0);
        
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> list, String s, int pos) {
    	// ONE: Where to end, how to end! Keep moving position, until to the end of string
        if (pos == s.length()) {
        	res.add(new ArrayList<String>(list));
        	return;
        }
        
        for (int i = pos; i < s.length(); i++) { // Dont repeat yourself. Start from a new point always.
        	// TWO: How to partition string. This is the SPIRIT
            String str = s.substring(pos, i + 1);
            if (!isPalindrome(str)) {
                continue;
            }
            list.add(str);
            helper(res, list, s, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
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
