ImplementTrie.java
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);

 This is an iterative approach for LC208, no recursion. Without recursion, not easy to do 211. Design Add and Search Words Data Structure

 */


class Trie {

    Node root = null;

    public Trie() {
        this.root = new Node();
    }
    
    public void insert(String word) {
        Node cur = root;

        for (char c : word.toCharArray()) {
            int pos = c - 'a';
            if (cur.children[pos] == null) {
                cur.children[pos] = new Node();
            }
            cur = cur.children[pos];
        }
        cur.isWord = true;
    }
    
    public boolean search(String word) {
        Node cur = root;

        for (char c : word.toCharArray()) {
            int pos = c - 'a';
            if (cur.children[pos] == null) {
                return false;
            }
            cur = cur.children[pos];
        }

        return cur.isWord;
    }
    
    public boolean startsWith(String prefix) {
        Node cur = root;

        for (char c : prefix.toCharArray()) {
            int pos = c - 'a';
            if (cur.children[pos] == null) {
                return false;
            }
            cur = cur.children[pos];
        }

        return true;
    }
}

class Node {
    Node[] children = new Node[26];
    boolean isWord;
}

