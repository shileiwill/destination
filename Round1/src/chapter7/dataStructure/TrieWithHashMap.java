package chapter7.dataStructure;

import java.util.HashMap;

public class TrieWithHashMap {
    private TrieNodeMap root;

    public TrieWithHashMap() {
        root = new TrieNodeMap();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	TrieNodeMap now = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (!now.children.containsKey(c)) {
        		now.children.put(c, new TrieNodeMap());
        	}
        	now = now.children.get(c);
        }
        now.hasWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNodeMap now = root;
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
        TrieNodeMap now = root;
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

class TrieNodeMap {
    public HashMap<Character, TrieNodeMap> children;
    public boolean hasWord;
    
    // Initialize your data structure here.
    public TrieNodeMap() {
    	children = new HashMap<Character, TrieNodeMap>();
        hasWord = false;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");