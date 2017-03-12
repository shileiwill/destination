package amazon;

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

A valid IP address must be in the form of A.B.C.D, where A,B,C and D are numbers from 0-255. The numbers cannot be 0 prefixed unless they are 0.

Example:

Given “25525511135”,

return [“255.255.11.135”, “255.255.111.35”]. (Make sure the returned strings are sorted in order)
 */
public class ValidIPAddresses {
	public ArrayList<String> restoreIpAddresses(String a) {
	    ArrayList<String> res = new ArrayList<String>();
	    
	    if (a.length() < 4 || a.length() > 12) {
	        return res;
	    }
	    
	    dfs(a, "", res, 0);
	    return res;
	}
	
	void dfs(String now, String prev, ArrayList<String> res, int count) {
	    if (count == 3 && isValid(now)) {
	        res.add(prev + now);
	        return;
	    }
	    // Because we stopped at the third section, need to leave something, so < now.length
	    for (int end = 1; end <= 3 && end < now.length(); end++) { // it is < now.length, without ==
	        String sub = now.substring(0, end);
	        if (isValid(sub)) {
	            dfs(now.substring(end), prev + sub + ".", res, count + 1); // Must be count + 1, not count++
	        }
	    }
	}
	
	boolean isValid(String s) {
	    if (s.charAt(0) == '0') {
	        return s.length() == 1;
	    }
	    
	    int val = Integer.parseInt(s);
	    return val >= 1 && val <= 255;
	}
}
