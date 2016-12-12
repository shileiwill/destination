package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {

    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
    
    public ShortestWordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                map.get(word).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(word, list);
            }
        }   
    }

    public int shortest(String word1, String word2) {
        List<Integer> word1Indexes = map.get(word1);
        List<Integer> word2Indexes = map.get(word2);
        
        // Either of them doesnt exist, return
        if (word1Indexes == null || word2Indexes == null) {
            return -1;
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
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");