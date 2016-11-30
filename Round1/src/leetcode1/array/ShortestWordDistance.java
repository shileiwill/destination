package leetcode1.array;

import java.util.ArrayList;
import java.util.List;
/**
 * 243. Shortest word distance. Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {

    public int shortestDistance2(String[] words, String word1, String word2) {
        List<Integer> word1Indexes = new ArrayList<Integer>();
        List<Integer> word2Indexes = new ArrayList<Integer>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                word1Indexes.add(i);
            } else if (word.equals(word2)) {
                word2Indexes.add(i);
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < word1Indexes.size(); i++) {
            for (int j = 0; j < word2Indexes.size(); j++) {
                int cur = Math.abs(word1Indexes.get(i) - word2Indexes.get(j));
                res = Math.min(res, cur);
            }
        }
        
        return res;
    }
    
    public int shortestDistance(String[] words, String word1, String word2) {
        int res = Integer.MAX_VALUE;
        int word1Idx = -1, word2Idx = -1;
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                word1Idx = i;
                if (word2Idx >= 0) {
                    res = Math.min(res, Math.abs(word1Idx - word2Idx));
                }
            } else if (word.equals(word2)) {
                word2Idx = i;
                if (word1Idx >= 0) {
                    res = Math.min(res, Math.abs(word1Idx - word2Idx));
                }
            }
        }
        
        return res;
    }

}
