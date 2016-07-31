package chapter6.search.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
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
		
		int res = wl.ladderLength("hit", "cog", wordList);
		System.out.println(res);
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
}
