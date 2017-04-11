package company.yahoo;

import java.util.ArrayList;
import java.util.List;
/**
 * 判断一个字符串是不是另一个字符串的subsequence。follow up是返回所有subsequence所在的位置
 */
public class IsSubsequence {

	public static void main(String[] args) {
		IsSubsequence is = new IsSubsequence();
		boolean res = is.isSubsequence("helloword", "hlor");
		System.out.println(res);
		
		for (int pos : is.positions) {
			System.out.print(pos + "==");
		}
	}

	List<Integer> positions = new ArrayList<Integer>();
	boolean isSubsequence(String s, String p) {
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < s.length() && pos2 < p.length()) {
			char c1 = s.charAt(pos1);
			char c2 = p.charAt(pos2);
			
			if (c1 == c2) {
				positions.add(pos1);
				pos1++;
				pos2++;
			} else {
				pos1++;
			}
		}
		
		if (pos2 == p.length()) {
			return true;
		}
		
		return false;
	}
}
