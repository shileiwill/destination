package chapter5.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 140. Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreak2 {
	
	// We can also build a dictionary, the use this in helper()
	/*
	 *      String sub = s.substring(start, i + 1);
            if (!dict[start][i]) {
                continue;
            }
	 */
    private boolean[][] buildDict(String s, Set<String> wordDict) {
        // From i to j, if it is a word
        boolean[][] res = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (wordDict.contains(sub)) {
                    res[i][j] = true;
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	long startTime = System.nanoTime();    
    	String s = "hereistexaswhereisveryhot";
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
    	
    	WordBreak2 wb2 = new WordBreak2();
    	List<String> list = wb2.wordBreak(s, wordDict);
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.println(estimatedTime);
    	for (String str : list) {
    		System.out.println(str + "--");
    	}
    }
    // Here is the solution
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if (!wordCanBreak(s, wordDict)) {
            return res;
        }
        boolean[][] dict = buildDict(s, wordDict);
        helper(s, wordDict, res, "", 0);
        
        return res;
    }
    
    public void helper(String s, Set<String> wordDict, List<String> res, String str, int start) {
        if (start == s.length()) { // 只要走到最后 说明构建出来了
            res.add(str);
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            
//	        if (!dict[start][i]) {
//	            continue;
//	        }
//	        helper(s, wordDict, res, str.length() == 0 ? sub : (str + " " + sub) , i + 1, dict);
            if (wordDict.contains(sub)) {
                helper(s, wordDict, res, str.length() == 0 ? sub : (str + " " + sub) , i + 1);
            } // Actually there is no backtracking here. 其实我们也没有list.add() 啥的。 正规的backtracking都是先add, helper, 之后remove
        }
    }
    
    // This is exactly from Word Break I
    public boolean wordCanBreak(String s, Set<String> dict) {
        // 前i个substring是否可以break
        boolean[] hash = new boolean[s.length() + 1];
        hash[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            hash[i] = false;
            for (int j = 0; j < i; j++) { // Iterate every previous digit
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
}
