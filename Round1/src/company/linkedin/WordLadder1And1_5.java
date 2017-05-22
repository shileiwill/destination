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
		WordLadder1And1_5 wordLadder = new WordLadder1And1_5();
		
		Set<String> dict = new HashSet<String>();
		dict.add("hit");
		dict.add("hog");
		dict.add("jet");
		dict.add("hue");
		dict.add("cit");
		dict.add("cat");
		dict.add("hat");
		dict.add("pek");
		
		wordLadder.ladderLengthSolutionBetter("lat", "pit", dict);
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
    
    // What if you are challenged of using a - z
    /**
     * // first build the buckets
// for each word, we replace each letter as '_' to get the bucket
// ex. "HITS" -> "_ITS" and "H_TS" and "HI_S" and "HIT_" then put the word into the bucket
// so the words in each bucket are the next word to each other, only differs in one letter
// then use BFS to traverse from the begin word, each time we meet a word, get all the buckets it could have and find the next word
// Time complexity: O(mn) + O(mn), n- number of words, m- average length of word, first one is build buckets, second is BFS
     */
    public List<String> ladderLengthSolutionBetter(String start, String end, Set<String> dict) {
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    	dict.add(start);
    	dict.add(end);
    	
    	for (String s : dict) {
    		char[] arr = s.toCharArray();
    		for (int i = 0; i < arr.length; i++) {
    			char original = arr[i];
    			
    			arr[i] = '_';
    			String s2 = new String(arr);
    			
    			if (!map.containsKey(s2)) {
    				map.put(s2, new HashSet<String>());
    			}
    			map.get(s2).add(s);
    			
    			arr[i] = original;
    		}
    	}
    	
    	Queue<String> queue = new LinkedList<String>();
    	Set<String> visited = new HashSet<String>();
    	Map<String, String> res = new HashMap<String, String>();
    	
    	queue.offer(start);
    	visited.add(start);
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		
    		for (int i = 0; i < size; i++) {
    			String now = queue.poll();
    			char[] arr = now.toCharArray();
    			
    			for (int j = 0; j < arr.length; j++) {
    				char original = arr[j];
    				
    				arr[j] = '_';
    				String s2 = String.valueOf(arr);
    				
    				for (String s3 : map.get(s2)) {
    					if (end.equals(s3)) {
    						res.put(s3, now);
    						return convertMapToList(res, start, end);
    					}
    					
    					if (!visited.contains(s3)) {
    						visited.add(s3);
    						res.put(s3, now);
    						queue.offer(s3);
    					}
    				}
    				
    				arr[j] = original;
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
