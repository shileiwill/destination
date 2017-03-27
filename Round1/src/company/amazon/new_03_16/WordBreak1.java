package company.amazon.new_03_16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
 * You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak1 {
	public boolean wordBreak(String s, List<String> dict) {
		boolean[] can = new boolean[s.length() + 1];
		can[0] = true;
		
		for (int i = 1; i <= s.length(); i++) {
			can[i] = false;
			for (int j = 0; j < i; j++) {
				String sub = s.substring(j, i);
				if (can[j] && dict.contains(sub)) {
					can[i] = true;
					break;
				}
			}
		}
		
		return can[s.length()];
	}
	
	// As there is max length for words, we can optimize this by first get the max length, and then ignore any longer ones
    public boolean wordBreak(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        
        for (int i = start; i < s.length(); i++) {
            for (int len = 1; len < s.length() - i; len++) { // add if <= maxLen here
                String sub = s.substring(i, i + len);
                if (wordDict.contains(sub) && wordBreak(s, wordDict, i + len)) {
                    return true;
                }
            }
        }
        
        return false;
    }
	
	public static void main(String[] args) {
//    	String s = "hereistexaswhereisveryhot";
		String s = "hewhere";
    	Set<String> wordDict = new HashSet<String>();
    	wordDict.add("he");
    	wordDict.add("where");
    	wordDict.add("is");
    	wordDict.add("texas");
    	wordDict.add("very");
    	wordDict.add("ve");
    	wordDict.add("ryhot");
    	wordDict.add("hot");
    	wordDict.add("here");
    	wordDict.add("reis");
    	
    	WordBreak1 wb2 = new WordBreak1();
    	boolean res = wb2.wordBreak(s, wordDict, 0);
    	System.out.println(res + "--");
//    	for (String str : list) {
//    		System.out.println(str + "--");
//    	}
	}
}

// 2 aspects can be improved. 1 build a 2 dimentional boolean array to indicate if word exist. 2, check if can partition first.
class WordBreak2 {
	public List<String> wordBreak(String s, Set<String> wordDict) {
		List<String> res = new ArrayList<String>();
		helper(res, s, wordDict, "", 0);
		return res;
	}
	
	void helper(List<String> res, String s, Set<String> dict, String now, int start) {
		if (start == s.length()) {
			res.add(now);
			return;
		}
		
		for (int i = start; i < s.length(); i++) {
			String sub = s.substring(start, i + 1);
			if (dict.contains(sub)) {
				helper(res, s, dict, now.length() == 0 ? sub : now + " " + sub, i + 1);
			}
		}
	}
}