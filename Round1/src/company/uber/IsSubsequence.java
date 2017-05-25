package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 392. Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, 
and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?
 */
public class IsSubsequence {

	public static void main(String[] args) {

	}

	public boolean isSubsequence2Pointers(String s, String t) {
		int p1 = 0;
		int p2 = 0;
		
		while (p2 < t.length()) {
			char c1 = s.charAt(p1);
			char c2 = t.charAt(p2);
			
			if (c1 == c2) {
				p1++;
				if (p1 == s.length()) {
					return true;
				}
			}
			p2++;
		}
		
		return false;
	}
	
	boolean isSubsequence(String s, String t) {
		// This could also be -> List<Integer>[] hash = new List<Integer>[26];
		Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>(); // Indexes of each char
		
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			
			if (!map.containsKey(c)) {
				map.put(c, new ArrayList<Integer>());
			}
			
			map.get(c).add(i);
		}
		
		int prev = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			List<Integer> list = map.get(c);
			
			// Binary search to find first >= prev
			int pos = binarySearch(list, prev);
			
			if (pos == -1) { // Not found
				return false;
			}
			
			prev = pos + 1;
		}
		
		return true;
	}
	
	int binarySearch(List<Integer> list, int key) {
		int left = 0;
		int right = list.size() - 1;
		
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			
			if (list.get(mid) == key) {
				return list.get(mid);
			}
			
			if (list.get(mid) < key) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (list.get(left) >= key) {
			return list.get(left);
		} else if (list.get(right) >= key) {
			return list.get(right);
		} else {
			return -1;
		}
	}
}
