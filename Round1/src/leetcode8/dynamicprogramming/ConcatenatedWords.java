package leetcode8.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 472. Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 */
public class ConcatenatedWords {
    // https://discuss.leetcode.com/topic/72113/java-dp-solution
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<String>();
        Set<String> preWords = new HashSet<String>();
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        for (String word : words) {
            if (canForm(word, preWords)) {
                res.add(word);
            }
            preWords.add(word);
        }
        
        return res;
    }
    
    boolean canForm(String word, Set<String> preWords) {
        if (preWords.isEmpty()) {
            return false;
        }
        
        boolean[] hash = new boolean[word.length() + 1];
        hash[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!hash[j]) {
                    continue;
                }
                if (preWords.contains(word.substring(j, i))) {
                    hash[i] = true;
                    break; // Find 1 is good enough
                }
            }
        }
        
        return hash[word.length()];
    }
    
    // https://discuss.leetcode.com/topic/72160/simple-java-trie-dfs-solution-144ms/5
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> res = new ArrayList<String>();
        Trie trie = new Trie();
        
        for (String word : words) {
            trie.insert(word);    
        }
        
        for (String s : words) {
            if (helper(s, trie)) {
                res.add(s);
            }
        }
        
        return res;
    }
    
    boolean helper(String s, Trie trie) {
        TrieNode cur = trie.root;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            cur = cur.children[index];
            if (cur == null)    return false;
            String suffix = s.substring(i+1);
            // tree.search(suffix) means the combination for this s consists of two words: cur + suffix
            // helper(suffix, tree) means there will be further level searching, the combination consists of more than two words
            if (cur.hasWord && (trie.search(suffix) || helper(suffix, trie)))  return true;
        }
        return false;
    }
}

class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode res = root.find(word, 0);
        return res != null && res.hasWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode res = root.find(prefix, 0);
        return res != null;
    }
}

class TrieNode {
    public TrieNode[] children;
    public boolean hasWord;
    
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        hasWord = false;
    }
    
    public void insert(String word, int index) {
        if (index == word.length()) {
            this.hasWord = true;
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            children[pos] = new TrieNode();
        }
        
        children[pos].insert(word, index + 1);
    }
    
    public TrieNode find(String word, int index) {
        if (index == word.length()) {
            return this;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            return null;
        }
        
        return children[pos].find(word, index + 1);
    }
}