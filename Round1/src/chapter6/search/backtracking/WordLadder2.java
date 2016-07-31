package chapter6.search.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 126. Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
 * @author Lei
 *
 */
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> ladders = new ArrayList<List<String>>(); // Final result holding all solutions
        Map<String, List<String>> neighbors = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        
        wordList.add(beginWord);
        wordList.add(endWord);
        
        bfs(neighbors, distance, beginWord, wordList);
        
        List<String> path = new ArrayList<String>();
        dfs(ladders, path, neighbors, distance, beginWord, endWord);
        
        return ladders;
    }
    
    void dfs(List<List<String>> ladders, List<String> path, Map<String, List<String>> neighbors, 
            Map<String, Integer> distance, String beginWord, String curWord) {
        path.add(curWord);
        
        if (beginWord.equals(curWord)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : neighbors.get(curWord)) {
                if (distance.containsKey(next) && distance.get(curWord) == distance.get(next) + 1) {
                    dfs(ladders, path, neighbors, distance, beginWord, next);
                }
            }
        }
        
        path.remove(path.size() - 1);        
    }
    
    void bfs(Map<String, List<String>> neighbors, Map<String, Integer> distance, String beginWord, Set<String> wordList){
        // Must have queue for breadth first search
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        
        distance.put(beginWord, 0);
        // Iterate all dictionary
        for (String s : wordList) {// Initialization
            neighbors.put(s, new ArrayList<String>());
        }
        
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            List<String> list = findNeighbors(cur, wordList);
            for (String next : list) {
                neighbors.get(next).add(cur);
                if (!distance.containsKey(next)) { // Out target is to build neighbors and distance
                    distance.put(next, distance.get(cur) + 1);
                    queue.offer(next);
                }
            }
            
        }
    }
    
    List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> expansion = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != word.charAt(i)) {
                    String str = word.substring(0, i) + ch + word.substring(i + 1);
                    if (wordList.contains(str)) {
                        expansion.add(str);
                    }
                }
            }
        }
        return expansion;
    }
}
