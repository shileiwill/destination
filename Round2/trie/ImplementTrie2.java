ImplementTrie2.java
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);

 
1804. Implement Trie II (Prefix Tree)
 Need to have counters to track. Pay attention to where we increment and decrement
 */


class Trie {

    Node root = null;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        root.insert(word, 0);
    }

    public int countWordsEqualTo(String word) {
        Node node = root.find(word, 0);
        if (node == null) {
            return 0;
        }

        return node.end;
    }
    
    public int countWordsStartingWith(String prefix) {
        Node node = root.find(prefix, 0);
        if (node == null) {
            return 0;
        }
        return node.count;
    }
    
    public void erase(String word) {
        root.erase(word, 0);
    }
}

class Node {
    Map<Character, Node> children;
    int end = 0;
    int count = 0;

    Node() {
        this.end = 0;
        this.children = new HashMap<>();
    }

    void insert(String word, int pos) {
        if (pos == word.length()) {
            this.end++;
            return;
        }

        char c = word.charAt(pos);
        if (!this.children.containsKey(c)) {
            this.children.put(c, new Node());
        }
        
        this.children.get(c).count += 1;
        this.children.get(c).insert(word, pos + 1);
    }

    Node find(String word, int pos) {
        if (pos == word.length()) {
            return this;
        }

        char c = word.charAt(pos);
        if (!this.children.containsKey(c)) {
            return null;
        }
        
        return this.children.get(c).find(word, pos + 1);
    }

    void erase(String word, int pos) {
        if (pos == word.length()) {
            this.end -= 1;
            return;
        }

        char c = word.charAt(pos);
        if (!this.children.containsKey(c)) {
            return;
        }

        this.children.get(c).count -= 1;
        this.children.get(c).erase(word, pos + 1);
    }
}

