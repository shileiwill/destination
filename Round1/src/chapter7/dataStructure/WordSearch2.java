package chapter7.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        TrieWithHashMap2 trie = new TrieWithHashMap2();
        
        for (String word : words) {
            trie.insert(word);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                helper(board, i, j, res, trie.root);
            }
        }
        
        return res;
    }
    
    int[] dx = {-1, 0, 1, 0};// Operations
    int[] dy = {0, 1, 0, -1};
    
    void helper(char[][] board, int i, int j, List<String> res, TrieNodeMap2 root) {
        if (root.hasWord == true) {
            if (!res.contains(root.str)) {
                res.add(root.str); // Dont stop here. it may contain a longer string
            }
        }
        
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || root == null) {
            return;
        }
        
        if (root.children.containsKey(board[i][j])) {
            for (int t = 0; t < 4; t++) {
                char now = board[i][j];
                board[i][j] = '#';
                helper(board, i + dx[t], j + dy[t], res, root.children.get(now));
                board[i][j] = now;
            }
        }
    }
}

class TrieWithHashMap2 {
    public TrieNodeMap2 root;

    public TrieWithHashMap2() {
        root = new TrieNodeMap2();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	TrieNodeMap2 now = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (!now.children.containsKey(c)) {
        		now.children.put(c, new TrieNodeMap2());
        	}
        	now = now.children.get(c);
        }
        now.hasWord = true;
        now.str = word; // set string
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNodeMap2 now = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (!now.children.containsKey(c)) {
        		return false;
        	}
        	now = now.children.get(c);
        }
        
        return now.hasWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNodeMap2 now = root;
        for (int i = 0; i < prefix.length(); i++) {
        	char c = prefix.charAt(i);
        	if (!now.children.containsKey(c)) {
        		return false;
        	}
        	now = now.children.get(c);
        }
        
        return true;
    }
}

class TrieNodeMap2 {
    public HashMap<Character, TrieNodeMap2> children;
    public boolean hasWord;
    public String str; // Add this string variable
    
    // Initialize your data structure here.
    public TrieNodeMap2() {
    	children = new HashMap<Character, TrieNodeMap2>();
        hasWord = false;
        str = "";
    }
}