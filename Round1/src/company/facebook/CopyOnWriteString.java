package company.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 
1. 白男 Behavior, short coding: 设计 copy-on-write string class
   e.g. stringclass s("abc");
         stringclass s1 = s; // no copy
         s = "bcd"; // copy
 */
public class CopyOnWriteString {

	public static void main(String[] args) {

	}

	List<Character> list = new ArrayList<Character>();
	
	CopyOnWriteString(String s) {
		for (char c : s.toCharArray()) {
			list.add(c);
		}
	}
	
	CopyOnWriteString change(String s) {
		CopyOnWriteString newStr = new CopyOnWriteString(s);
		return newStr;
	}
}

/**
 * 
 * 给两个数 n1, n2. n2 is n1 without 1 digit, e.g. n1 = 123, then n2 can be 12, 13 or 23. 现在知道n1+n2的和是多少， 求n1 和 n2 的可能值
 */
