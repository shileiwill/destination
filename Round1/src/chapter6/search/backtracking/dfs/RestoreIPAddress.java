package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * @author Lei
 *
 */
public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        // List makes it easier to operate, we will convert to IP String by adding dots
        // The 17th Phone number uses StringBuilder, but it add/remove only char
        List<String> list = new ArrayList<String>();

        // Make it more efficient
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        
        helper(res, list, s, 0);
        return res;
    }
    
    private void helper(List<String> res, List<String> list, String s, int pos) {
    	// ONE: Where to end, how to end! Keep moving position, until to the end of string
    	if (list.size() == 4) { // 4 segments && cursor is at the end of string, done.
    	    if (pos != s.length()) { // Put this if here, rather than above if statement, is more efficient. This will return immediately
    	        return; // 4 elements already, but not make full use of string yet.
    	    }
    	    StringBuilder sb = new StringBuilder();
    	    for (String str : list) {
    	        sb.append(str);
        	    sb.append("."); // Just add, we will remove the last dot later. This avoids N if judgements
    	    }
    	    sb.deleteCharAt(sb.length() - 1);
    	    res.add(sb.toString());
    	    return;
    	}
        for (int i = pos; i < s.length() && i <= pos + 3; i++) { // Dont repeat yourself. Start from a new point always.
        	// TWO: How to partition string. This is the SPIRIT
        	// A much clearer way is added in for loop
            // 	if (i - pos == 3) { // Over 4 digits is non-valid for sure
            // 	    return;
            // 	}
            String str = s.substring(pos, i + 1);
            if (!isValidIPSegment(str)) {
                continue;
            }
            list.add(str);
            helper(res, list, s, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
    }
    
    private boolean isValidIPSegment(String s) {
        if (s.charAt(0) == '0') {
            return s.equals("0"); // To avoid 00, 000
        }
        
        int val = Integer.valueOf(s);
        return val >= 0 && val <= 255;
    }
}
