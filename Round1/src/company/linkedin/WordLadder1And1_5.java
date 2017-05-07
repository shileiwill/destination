package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder1And1_5 {

	public static void main(String[] args) {

	}

    // Another easier way
    public int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordList.remove(beginWord); // Remove from dictionary
        int levels = 1;
        
        while (!queue.isEmpty()) {
            
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) { // Could also continue when it is the same char with itself
                        String newWord = word.substring(0, i) + String.valueOf(c) + word.substring(i + 1);
                        
                        if (newWord.equals(endWord)) {
                            return levels + 1;
                        }
                        
                        if (wordList.contains(newWord)) {
                            queue.offer(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
            }
            
            levels++;
        }
        
        return -1;
    }
    
    // I want to get the solution
    public List<String> ladderLengthSolution(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) {
            List<String> res = new ArrayList<>();
            res.add(beginWord);
        	return res;
        }
        
        Map<String, String> map = new HashMap<String, String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordList.remove(beginWord); // Remove from dictionary
        
        while (!queue.isEmpty()) {
            
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) { // Could also continue when it is the same char with itself
                        String newWord = word.substring(0, i) + String.valueOf(c) + word.substring(i + 1);
                        
                        if (newWord.equals(endWord)) {
                        	map.put(newWord, word);
                        	List<String> res = convertMapToList(map, beginWord, endWord);
                            return res;
                        }
                        
                        if (wordList.contains(newWord)) {
                        	map.put(newWord, word);
                            queue.offer(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    List<String> convertMapToList(Map<String, String> map, String beginWord, String endWord) {
    	LinkedList<String> res = new LinkedList<String>();
    	
    	while (!map.get(endWord).equals(beginWord)) {
    		res.add(endWord);
    		endWord = map.get(endWord);
    	}
    	
    	res.add(endWord);
    	res.add(beginWord);
    	
    	for (int i = res.size() - 1; i >= 0; i--) {
    		System.out.print(res.get(i) + "==");
    	}
    	return res;
    }
    
    // 2-way BFS
    public int ladderLength(String start, String end, List<String> dict) {
        if (!dict.contains(end)) {
            return 0;
        }
        
        Set<String> left = new HashSet<String>();
        Set<String> right = new HashSet<String>();
        
        left.add(start);
        right.add(end);
        dict.remove(start);
        dict.remove(end);
        
        int level = 1;
        
        while (!left.isEmpty()) {
            Set<String> next = new HashSet<String>();
            
            for (String now : left) {
                for (int j = 0; j < now.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String nextWord = now.substring(0, j) + c + now.substring(j + 1);
                        
                        if (right.contains(nextWord)) {
                            return level + 1;
                        }
                
                        if (dict.contains(nextWord)) {
                            next.add(nextWord);
                            dict.remove(nextWord);
                        }
                    }
                }
            }
            
            left = right.size() < next.size() ? right : next;
            right = right.size() < next.size() ? next : right;
            level++;
        }
        
        return 0;
    }

}
