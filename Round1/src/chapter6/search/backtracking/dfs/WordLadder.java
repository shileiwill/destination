package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 127. Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 * @author Lei
 *
 */
public class WordLadder {

	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		String[] arr = {"hot","dot","dog","lot","log"};
		Set<String> wordList = new HashSet<String>(Arrays.asList(arr));
		
		wl.ladderLengthSolution("hit", "log", wordList);
//		System.out.println(res);
	}

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordList.remove(beginWord); // Remove from dictionary
        
        int thisLevelCount = 1;
        int nextLevelCount = 0;
        int levels = 2;
        
        int len = beginWord.length();
        
        while (!queue.isEmpty()) {
            String str = queue.poll();
            thisLevelCount--;
            
            for (int i = 0; i < len; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == str.charAt(i)) {
                        continue;
                    }
                    
                    String newStr = replace(str, i, ch);
                    
                    if (newStr.equals(endWord)) {
                        // Found a solution
                        return levels;
                    }
                    
                    if (wordList.contains(newStr)) {
                        queue.offer(newStr);
                        wordList.remove(newStr);
                        nextLevelCount++;
                    }
                }
            }
            
            if (thisLevelCount == 0) { // This level done, go to next
                thisLevelCount = nextLevelCount;
                nextLevelCount = 0;
                levels++;
            }
        }
        
        return 0;
    }
    
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
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
    
    public int ladderLengthIterative(String beginWord, String endWord, List<String> wordAsList) {
        if(!wordAsList.contains(endWord)) return 0;
        
        Set<String> wordList = new HashSet<String>(wordAsList);
        Set<String> start = new HashSet<String>();
        Set<String> end = new HashSet<String>();
        int length = 1;
        start.add(beginWord); end.add(endWord);
        wordList.remove(beginWord); wordList.remove(endWord);
        
        while(!start.isEmpty()){
            Set<String> next = new HashSet<String>();
            for(String word: start){
                char[] wordArray = word.toCharArray();
                for(int i=0; i<word.length(); i++){
                    char old = wordArray[i];
                    for(char c='a'; c<='z'; c++){
                        wordArray[i] = c;
                        String str = String.valueOf(wordArray);
                        if(end.contains(str))
                            return length+1;
                        if(wordList.contains(str)){
                            next.add(str);
                            wordList.remove(str);
                        }
                    }
                    wordArray[i] = old; // Change back
                }
            }
            start = next.size() < end.size() ? next: end;
            end = start.size() < end.size() ? end : next;
            length++;
        }
        return 0;
    }
    
    // Two-end BFS Recursion
    public int ladderLength(String start, String end, List<String> dict) {
    
        if (!dict.contains(end)) {
            return 0;
        }
        
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        
        set1.add(start);
        set2.add(end);
        
        return helper(dict, set1, set2, 1);
    }
    
    int helper(List<String> dict, Set<String> set1, Set<String> set2, int level) {
        if (set1.isEmpty()) return 0;
        
        if (set1.size() > set2.size()) return helper(dict, set2, set1, level);
        
        // remove words from both ends
        for (String word : set1) { dict.remove(word); };
        for (String word : set2) { dict.remove(word); };
        
        // the set for next level
        Set<String> set = new HashSet<String>();
        
        // for each string in the current level
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                
                // change letter at every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch; // This guy doesnt need to change back
                    String word = new String(chars);
                    
                    // found the word in other end(set)
                    if (set2.contains(word)) {
                        return level + 1;
                    }
                    
                    // if not, add to the next level
                    if (dict.contains(word)) {
                        set.add(word);
                    }
                }
            }
        }
        
        return helper(dict, set2, set, level + 1);
    }
    
}
