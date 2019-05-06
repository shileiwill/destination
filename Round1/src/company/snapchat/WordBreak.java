package company.snapchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 139.
 * 重点！
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 * @author lei2017
 *
 */
public class WordBreak {

	public static void main(String[] args) {
		String s = "applepenapple";
		List<String> dict = new ArrayList<>();
		dict.add("apple");
		dict.add("pen");
		
		WordBreak wb2 = new WordBreak();
		List<String> res = wb2.wordBreak(s, dict);
		for(String s2 : res) {
			System.out.println(s2);
		}
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

	private void helper(List<String> res, String s, List<String> dict, String now, int start) {
		if (start == s.length()) {
			res.add(now);
			return;
		}
		
		for (int len = 1; len <= s.length() - start; len++) { // 有start和length就能决定头和尾
			String sub = s.substring(start, start + len);
			
			if (dict.contains(sub)) {
				helper(res, s, dict, start == 0 ? sub : now + " " + sub, start + len); // now + " " + sub? YES!
			}
		}
	}
	
	/**
	 * 给你一个dictionary，比如["robin", "rob", "robinghodd", "robinhood", "hood", "in"]
找到最长的word，这个word可以由其他字符串组成
	 */
}
