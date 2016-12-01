package leetcode1.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 245. This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistance3 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int res = Integer.MAX_VALUE;
        int word1Idx = -1, word2Idx = -1;
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                word1Idx = i;
                if (word2Idx >= 0 && word1Idx != word2Idx) {
                    res = Math.min(res, Math.abs(word1Idx - word2Idx));
                }
            }
            if (word.equals(word2)) {
                word2Idx = i;
                if (word1Idx >= 0 && word1Idx != word2Idx) {
                    res = Math.min(res, Math.abs(word1Idx - word2Idx));
                }
            }
        }
        
        return res;
    }
    
    public int shortestWordDistance2(String[] words, String word1, String word2) {
        List<Integer> word1Indexes = new ArrayList<Integer>();
        List<Integer> word2Indexes = new ArrayList<Integer>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                word1Indexes.add(i);
            }
            if (word.equals(word2)) {
                word2Indexes.add(i);
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < word1Indexes.size(); i++) {
            for (int j = 0; j < word2Indexes.size(); j++) {
                int cur = Math.abs(word1Indexes.get(i) - word2Indexes.get(j));
                if (cur == 0) {
                    continue;
                }
                res = Math.min(res, cur);
            }
        }
        
        return res;
    }
}
