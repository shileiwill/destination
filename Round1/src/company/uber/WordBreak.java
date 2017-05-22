package company.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordBreak {

	public static void main(String[] args) {

	}

	boolean canBreak(String s, Set<String> dict) {
		boolean[] hash = new boolean[s.length() + 1];
		hash[0] = true;
		
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (hash[j]) {
					String sub = s.substring(j, i);
					if (dict.contains(sub)) {
						hash[i] = true;
						break;
					}
				}
			}
		}
		
		return hash[s.length()];
	}
	
	// To find all solutions
	List<String> wordBreak(String s, List<String> dict) {
		List<String> res = new ArrayList<String>();
		helper(res, s, dict, "", 0);
		return res;
	}

	private void helper(List<String> res, String s, List<String> dict, String now, int pos) {
		if (pos == s.length()) {
			res.add(now);
			return;
		}
		
		for (int len = 1; len <= s.length() - pos; len++) {
			String sub = s.substring(pos, pos + len);
			
			if (dict.contains(sub)) {
				helper(res, s, dict, pos == 0 ? sub : " " + sub, pos + len);
			}
		}
	}
	
}
