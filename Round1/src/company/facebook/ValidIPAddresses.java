package company.facebook;

import java.util.ArrayList;

/**
 * 468. Given a string containing only digits, restore it by returning all possible valid IP address combinations.

A valid IP address must be in the form of A.B.C.D, where A,B,C and D are numbers from 0-255. The numbers cannot be 0 prefixed unless they are 0.

Example:

Given “25525511135”,

return [“255.255.11.135”, “255.255.111.35”]. (Make sure the returned strings are sorted in order)

FB: valid IP in string format and return the uint32 format - 32 位无符号整数
‘1.2.3.4’  -> 0x01020304, 如果不是valid IP string 怎么办， 自己定义signature
和利口的一样 只不过加一步把string 转换 成32bit的uint32， 注意处理各种corner case 和 返回值， 如果不valid 你返回什么？
0x0 - 0xffffffff 全是有用的。这里就需要讨论， 你怎么想的 改成你想要的signature或者其他的什么

 */
public class ValidIPAddresses {
	public static void main(String[] args) {
		ValidIPAddresses vip = new ValidIPAddresses();
		ArrayList<String> res = vip.restoreIpAddresses("25525511135");
		
		for (String s : res) {
			System.out.println(s);
		}
	}
	
	public ArrayList<String> restoreIpAddresses(String a) {
	    ArrayList<String> res = new ArrayList<String>();
	    
	    if (a.length() < 4 || a.length() > 12) {
	        return res;
	    }
	    
	    //dfs(a, "", res, 0);
	    ArrayList<String> list = new ArrayList<String>();
	    helper(res, list, a, 0);
	    return res;
	}
	
	// Mine is far better!
	private void helper(ArrayList<String> res, ArrayList<String> list, String s, int pos) {
		if (pos == s.length()) {
			if (list.size() == 4) {
				res.add(transform(list));
			}
			return;
		}
		
		if (list.size() >= 4) {
			return;
		}
		
		for (int len = 1; len <= 3 && pos + len <= s.length(); len++) {
			String sub = s.substring(pos, pos + len);
			if (isValid(sub)) {
				list.add(sub);
				helper(res, list, s, pos + len);
				list.remove(list.size() - 1);
			}
		}
	}

	private String transform(ArrayList<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s + ".");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
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
