package company.facebook;
// 给一个字典包括很多字符串(e.g., abcd, dhfyf)，然后给定一个字符串查看字典中是否包含这个字符串。字符串中可能包括*，*可以匹配任何字符。我用的Trie。
/**
 * 211. Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

設計一個class, add(word): 把word加進去， 然後 isMember(word): 如果word之前有加進去的話， return True
難點是: add(Apple) -> isMember(A*pl*) 要return true，isMember(*****) 也要return true.

given a list of words, find whether a given target word exists. Should support “.” which matches any character. 
Follow up: support “*” which matchs 0 or more characters

加上了support for '*'. 关键是当search word遇到'*'，要跳过所有可能连续的'*'继续寻找下一个非'*'的char。
在字典数据结构的Trie中就对于找当前node的所有对应的子孙nodes（若是'.'就找所有子孙）。然后再循环每个子孙node递归。
一个edge case就是若word以连续的'*'结尾的话就直接返回true，因为'*'之前的prefix在字典中已经找到了

不对，应该是这样，分两种情况，因为他可以匹配0个字符，所以直接可以用*后面那个字符在当前层搜索，如果失败，因为*可以匹配任何多个字符，
那么就对当前层不是NULL的CHILDREN进行递归，然后在下一层再对*后面那个字符进行搜索
 */
public class TrieMatch {

	public static void main(String[] args) {
		String[] dict = {"hello", "world", "word", "hellp"};
		TrieMatch tm = new TrieMatch();
		TrieNode root = new TrieNode();
		
		for (String s : dict) {
			root.insert(s, 0);
		}
		
		TrieNode node = root.search("wor*d", 0);
		System.out.println(node == null);
		if (node != null) {
			System.out.println(node.hasWord);
		}
	}

}

class TrieNode {
	TrieNode[] children = null;
	boolean hasWord = false;
	
	TrieNode() {
		children = new TrieNode[26];
	}
	
	void insert(String word, int pos) {
		if (pos == word.length()) {
			this.hasWord = true;
			return;
		}
		
		if (this.children[word.charAt(pos) - 'a'] == null) {
			this.children[word.charAt(pos) - 'a'] = new TrieNode();
		}
		this.children[word.charAt(pos) - 'a'].insert(word, pos + 1);
	}
	
	TrieNode search(String word, int pos) {
		if (pos == word.length()) {
			return this;
		}
		
		if (word.charAt(pos) == '*') {
			for (TrieNode child : this.children) {
				if (child != null) {
					TrieNode node = child.search(word, pos + 1);
					if (node != null && node.hasWord) {
						return node;
					}
				}
			}
		} else {
			TrieNode child = this.children[word.charAt(pos) - 'a'];
			if (child == null) {
				return null;
			} else {
				return child.search(word, pos + 1);
			}
		}
		
		return null;
	}
}
