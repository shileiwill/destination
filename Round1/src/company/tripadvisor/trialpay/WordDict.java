package company.tripadvisor.trialpay;

import java.util.HashSet;
import java.util.Set;

public class WordDict {

	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("hello");
		dict.add("world");
		dict.add("word");
		dict.add("hell");
		dict.add("helloworld");
		dict.add("lldef");
		
		WordDict wd = new WordDict();
		wd.buildTrie(dict);
		
		char[][] M = {{'h', 't', 'l', 'd'}, {'e', 'l', 'r', 'o'}, {'f', 'l', 'o', 'w'}, {'e', 'd', 'r', 'o'}};
		Set<String> res = wd.wordDict(M);
		
		for (String s : res) {
			System.out.println(s);
		}
	}
	
	TrieNode root = new TrieNode();
	
	void buildTrie(Set<String> dict) {
		for (String word : dict) {
			root.insert(word, 0);
		}
	}
	
	Set<String> wordDict(char[][] M) {
		Set<String> res = new HashSet<String>();
		int m = M.length;
		int n = M[0].length;
		Set<Integer> visited = new HashSet<Integer>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = M[i][j];
				if (root.startsWith(c + "", 0)) {
					StringBuilder sb = new StringBuilder();
					sb.append(M[i][j]);
					visited.add(i * n + j);
					dfs(M, sb, res, i, j, visited);
					visited.remove(i * n + j);
					sb.setLength(sb.length() - 1);
				}
			}
		}
		
		return res;
	}

	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	void dfs(char[][] M, StringBuilder sb, Set<String> res, int i, int j, Set<Integer> visited) {
		if (root.search(sb.toString(), 0) != null) {
			res.add(sb.toString());
		}
		
		for (int[] dir : direction) {
			int x = i + dir[0];
			int y = j + dir[1];
			int id = x * M[0].length + y;
			
			if (x >= 0 && x < M.length && y >= 0 && y < M[0].length && !visited.contains(id)) {
				sb.append(M[x][y]);
				if (root.startsWith(sb.toString(), 0)) {
					visited.add(id);
					dfs(M, sb, res, x, y, visited);
					visited.remove(id);
				}
				sb.setLength(sb.length() - 1);
			}
		}
	}
}
class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean hasWord = false;
	
	void insert(String s, int index) {
		if (index == s.length()) {
			this.hasWord = true;
			return;
		}
		
		int pos = s.charAt(index) - 'a';
		if (children[pos] == null) {
			children[pos] = new TrieNode();
		}
		
		children[pos].insert(s, index + 1);
	}
	
	TrieNode search(String s, int index) {
		if (index == s.length()) {
			if (this.hasWord) {
				return this;
			}
			return null;
		}
		
		int pos = s.charAt(index) - 'a';
		if (children[pos] == null) {
			return null;
		}
		
		return children[pos].search(s, index + 1);
	}
	
	boolean startsWith(String s, int index) {
		if (index == s.length()) {
			return true;
		}
		
		int pos = s.charAt(index) - 'a';
		if (children[pos] == null) {
			return false;
		}
		
		return children[pos].startsWith(s, index + 1);
	}
}