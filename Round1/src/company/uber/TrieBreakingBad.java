package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体介绍一下这个题， input是String array(大小写都有), 和一个String name（全小写） 
 * 输出要求有点复杂，在name一个个substring里找，如果这个substring没有在array里出现过，就直接输出。
 * 如果在array里出现过，就找尽量长的可以在array里匹配得到的，然后把第一个字母变成大写并且前后加上符号再输出。 
 * 比如array = {“b”, “Bc”, “e”} name = “abcde”; 
 * output就是ad 这里因为b可以匹配到b，bc可以匹配到bc而且更长所以答案就是这样。
 * 
 * 写出一个auto complete的程序 要求程序可以跑test。当时写出了trie 就差最后的dfs没写完 面试官不允许我用boolean去标记 说是不适合同一时间很多搜索
 */
public class TrieBreakingBad {

	TrieNode root = new TrieNode();
	public static void main(String[] args) {
		TrieBreakingBad t = new TrieBreakingBad();
		
		String source = "helloword";
		String[] dict = {"abc", "dec", "UDA", "abcde", "bcdef", "abfeU"};
		
		// Build Trie
		for (String s : dict) {
			t.root.insert(s, 0);
		}
		
		List<String> list = t.autoComplete("abc");
		for (String s : list) {
			System.out.println(s);
		}
//		String res = "";
//		for (int i = 0; i < source.length(); i++) {
//			for (int j = i + 1; j < source.length(); j++) {
//				String sub = source.substring(i, j);
//				if (!root.startsWith(sub)) {
//					break;
//				}
//				if (sub.length() > res.length()) {
//					res = sub;
//				}
//			}
//		}
	}

	List<String> autoComplete(String s) {
		TrieNode node = root.search(s, 0);
		
		List<String> res = new ArrayList<String>();
		if (node == null) {
			return res;
		}
		
		dfs(res, node); 
		return res;
	}

	private void dfs(List<String> res, TrieNode node) {
		for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
			TrieNode childNode = child.getValue();
			
			if (childNode.hasWord) {
				res.add(childNode.word);
			}
			
			dfs(res, childNode);
		}
	}
	
	static class TrieNode {
		Map<Character, TrieNode> children = null;
		boolean hasWord = false;
		String word = null;
		
		TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
		}
		
		void insert(String s, int pos) {
			if (pos == s.length()) {
				this.hasWord = true;
				this.word = s;
				return;
			}
			
			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				this.children.put(now, new TrieNode());
			}
			
			this.children.get(now).insert(s, pos + 1);
		}
		
		TrieNode search(String s, int pos) {
			if (pos == s.length()) {
				return this;
			}
			
			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				return null;
			}
			
			return this.children.get(now).search(s, pos + 1);
		}
		
		boolean startsWith(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}
			
			return true;
		}
		
		boolean contains(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}
			
			return node.hasWord;
		}
	}
}
