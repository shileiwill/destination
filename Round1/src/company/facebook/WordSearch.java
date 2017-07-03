package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
// 212, 79
public class WordSearch {

	public static void main(String[] args) {

	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int m = -1;
	int n = -1;
	public boolean exist(char[][] board, String word) {
		Set<Integer> visited = new HashSet<Integer>();
		
		m = board.length;
		n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					visited.add(i * n + j);
					boolean res = dfs(board, word, i, j, visited, 1);
					if (res) {
						return true;
					}
					visited.remove(i * n + j);
				}
			}
		}
		
		return false;
	}
	private boolean dfs(char[][] board, String word, int i, int j, Set<Integer> visited, int pos) {
		if (pos == word.length()) {
			return true;
		}
		
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			int newId = x * n + y;
			
			if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == word.charAt(pos) && !visited.contains(newId)) {
				visited.add(newId);
				boolean res = dfs(board, word, x, y, visited, pos + 1);
				if (res) {
					return true;
				}
				visited.remove(newId);
			}
		}
		
		return false;
	}
}

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

给定一个字典 和 一个字符串数组 找出所有可能出现在字符串中的字符可以组成字典里面的字，  也就是字符串数组是个2 维char矩阵，每个字符可以走八个方向。DFS 解决 然后问了下为啥不用BFS
The magic is to move trieNode
 */
class WordSearch2 {
	public static void main(String[] args) {
		char[][] board = {
				  {'o','a','a','n'},
				  {'e','t','a','e'},
				  {'i','h','k','r'},
				  {'i','f','l','v'}
		};
		
		String[] words = {"oath","pea","eat","rain"};
		
		WordSearch2 ws2 = new WordSearch2();
		List<String> res = ws2.findWords(board, words);
		
		for (String s : res) {
			System.out.println(s);
		}
	}
	
	int m = 0;
	int n = 0;
    public List<String> findWords(char[][] board, String[] words) {
    	m = board.length;
    	n = board[0].length;
    	
        TrieNode root = new TrieNode();
        
        for (String word : words) {
            root.insert(word, 0);
        }
        
        Set<Integer> visited = new HashSet<Integer>();
        List<String> res = new ArrayList<String>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
            	visited.add(i * n + j);
                dfs(board, res, i, j, root, visited);
                visited.remove(i * n + j);
            }
        }
        
        return res;
    }
    
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    void dfs(char[][] board, List<String> res, int i, int j, TrieNode root, Set<Integer> visited) {
        if (root == null) {
            return;
        }
        
        if (root.hasWord) {
            if (!res.contains(root.str)) {
                res.add(root.str);
            }
        }
        
        if (root.children.containsKey(board[i][j])) {
            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];
                int id = x * n + y;
                
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited.contains(id)) {
                    visited.add(id);
                    dfs(board, res, x, y, root.children.get(board[i][j]), visited);
                    visited.remove(id);
                }
            }
        }
    }
    
    static class TrieNode {
        Map<Character, TrieNode> children = null;
        boolean hasWord = false;
        String str;
        
        TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
        }
        
        void insert(String word, int pos) {
            if (pos == word.length()) {
                this.hasWord = true;
                this.str = word;
                return;
            }
            
            char key = word.charAt(pos);
            TrieNode node = children.get(key);
            
            if (node == null) {
                children.put(key, new TrieNode());
            }
            
            children.get(key).insert(word, pos + 1);
        }
        
        TrieNode search(String word, int pos) {
            if (pos == word.length()) {
                return this;
            }
            
            int key = word.charAt(pos);
            TrieNode node = children.get(key);
            
            if (node == null) {
                return null;
            }
            
            return children.get(key).search(word, pos + 1);
        }
        
        boolean hasWord(String word) {
            TrieNode node = search(word, 0);
            return node != null && node.hasWord;
        }
        
        // To solve following question
        TrieNode containsPrefix(String word, int pos) { // Word is in sentence
        	if (pos == word.length()) {
        		if (this.hasWord) {
        			return this;
        		}
        		return null; // Return itself, nothing to replace
        	}
        	
        	int key = word.charAt(pos);
        	TrieNode node = this.children.get(key);
        	
        	if (node == null) {
        		return null; //Nothing to replace
        	}
        	
        	if (node.hasWord) {
        		return node; // Once find a prefix, just return
        	}
        	
        	return children.get(key).search(word, pos + 1);
        }
    }
}