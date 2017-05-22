package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ["this", "hi", "his", "is", "word"]
然后再给一字串例如
"thisisaword"
要找到当中在字典里面出现过的substring
所以应该要返回
["this", "hi", "his", "is", "is", "word"]
注意"is"被返回了两次因为确实出现了两次
 */
public class StringInDictionary {

	public static void main(String[] args) {
		String[] arr = {"this", "hi", "his", "is", "word"};
		String source = "thisisaword";
		List<String> asList = Arrays.asList(arr);
		Set<String> dict = new HashSet<String>(asList);
		
		StringInDictionary sid = new StringInDictionary();
		sid.findByTrie(source, dict);
	}

	List<String> find(String source, Set<String> dict) {
		List<String> res = new ArrayList<String>();
		int maxLength = Integer.MIN_VALUE;
		for (String s : dict) {
			maxLength = Math.max(maxLength, s.length());
		}
		
		for (int len = 1; len <= maxLength; len++) {
			for (int start = 0; start <= source.length() - len; start++) {
				String sub = source.substring(start, start + len);
				
				if (dict.contains(sub)) {
					System.out.println(sub);
					res.add(sub);
				}
			}
		}
		
		return res;
	}
	
	void findByTrie(String source, Set<String> dict) {
		TrieNode root = new TrieNode();
		
		for (String s : dict) {
			root.insert(s, 0);
		}
		
		System.out.println(root);
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean hasWord = false;
	
	void insert(String s, int pos) {
		if (pos == s.length()) {
			this.hasWord = true;
			return;
		}
		
		int index = s.charAt(pos) - 'a';
		if (this.children[index] == null) {
			this.children[index] = new TrieNode();
		}
		
		this.children[index].insert(s, pos + 1);
	}
	
	TrieNode search(String s, int pos) {
		if (pos == s.length()) {
			return this;
		}
		
		int index = s.charAt(pos) - 'a';
		if (this.children[index] == null) {
			return null;
		}
		
		return this.children[index].search(s, pos + 1);
	}
	
	boolean startsWith(String s, int pos) {
		TrieNode node = search(s, pos);
		
		return node == null ? false : true;
	}
	
	boolean contains(String s, int pos) {
		TrieNode node = search(s, pos);
		
		return node == null || !node.hasWord ? false : true;
	}
}