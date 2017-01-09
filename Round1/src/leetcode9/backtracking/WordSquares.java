package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.List;
/**
 * 425. Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 */
public class WordSquares {
// https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16/2
    class TrieNode {
        List<String> startWith;
        TrieNode[] children;
        
        TrieNode() {
            startWith = new ArrayList<String>();
            children = new TrieNode[26];
        }
    }
    
    class Trie {
        TrieNode root = new TrieNode();
        
        Trie(String[] words) {
            for (String word : words) {
                TrieNode cur = root; // Every new word should start from root
                char[] arr = word.toCharArray();
                for (char c : arr) {
                    int index = c - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new TrieNode();
                    }
                    cur.children[index].startWith.add(word);
                    cur = cur.children[index];
                }
            }
        }
        
        List<String> findByPrefix(String prefix) {
            List<String> res = new ArrayList<String>();
            TrieNode cur = root;
            
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) { // No such string
                    return res;
                }
                
                cur = cur.children[index];
            }
            res.addAll(cur.startWith); // Add the last
            
            return res;
        }
    }
    
    public List<List<String>> wordSquaresTrie(String[] words) {
		List<List<String>> res = new ArrayList<List<String>>();
		Trie trie = new Trie(words);
		List<String> list = new ArrayList<String>();
		int len = words[0].length();
		
		for (String word : words) {
		    list.add(word);
		    helper(len, trie, list, res);
		    list.remove(list.size() - 1);
		}
		
		return res;
    }
    
    void helper(int len, Trie trie, List<String> list, List<List<String>> res) {
        if (list.size() == len) {
            res.add(new ArrayList<String>(list));
            return;
        }
        
        StringBuilder prefixBuilder = new StringBuilder();
        int pos = list.size();
        for (String str : list) {
            prefixBuilder.append(str.charAt(pos));
        }
        
        List<String> candidates = trie.findByPrefix(prefixBuilder.toString());
        
        for (String can : candidates) {
            list.add(can);
            helper(len, trie, list, res);
            list.remove(list.size() - 1);
        }
    }
    
	public static void main(String[] args) {
		WordSquares ws = new WordSquares();
		String[] words = {"area","lead","wall","lady","ball"};
		List<List<String>> permutation = ws.wordSquares(words);
//		
//		for (List<String> list : permutation) {
//			for (String str : list) {
//				System.out.print(str + "==");
//			}
//			System.out.println();
//		}
	}

    public List<List<String>> wordSquares(String[] words) {
		List<List<String>> res = getPermutation(words);
		
		return res;
    }
    
    List<List<String>> getPermutation(String[] words) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	List<String> list = new ArrayList<String>();
    	
    	helper(res, list, words);
    	return res;
    }
    
    void helper(List<List<String>> res, List<String> list, String[] words) {
    	if (list.size() == words[0].length()) { // Cautious here, length of the word
    	    if (validWordSquare(list)) {
        		res.add(new ArrayList<String>(list));
    	    }
    		return;
    	}
    	
    	for (int i = 0; i < words.length; i++) {
    		list.add(words[i]);
    		helper(res, list, words);
    		list.remove(list.size() - 1);
    	}
    }
    
    public boolean validWordSquare(List<String> words) {
        int len = words.size();
        
        for (int i = 0; i < len; i++) { // Row
            String word = words.get(i);
            
            for (int j = 0; j < word.length(); j++) { // Col
                if (j >= len || words.get(j).length() <= i || words.get(j).charAt(i) != word.charAt(j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
