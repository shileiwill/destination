package company.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
// 212, 79


class Solution {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int M = -1;
    int N = -1;

    // Solution 1: Use DFS, dont use directions, dont use visited set, backtracking
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) { // Start with the first char
                    boolean res = helper(board, word, i, j, 0);
                    if (res) { // Only if found, return. If not, continue searching
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    boolean helper(char[][] board, String word, int i, int j, int start) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || word.charAt(start) != board[i][j]) {
            return false;
        }
        
        board[i][j] = '#'; // Mark this as read. Never come back!
        boolean res = helper(board, word, i - 1, j, start + 1) || helper(board, word, i + 1, j, start + 1) || 
            helper(board, word, i, j - 1, start + 1) || helper(board, word, i, j + 1, start + 1);
            
        board[i][j] = word.charAt(start); // Reset. This is backtrack
        return res;
    }

    // Solution 2: Use DFS, backtracking
    public boolean exist(char[][] board, String word) {
        M = board.length;
        N = board[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[M][N];
                    visited[i][j] = true;
                    boolean res = dfs(board, i, j, visited, word, 1);
                    if (res) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean dfs(char[][] board, int i, int j, boolean[][] visited, String word, int pos) {
        if (pos == word.length()) {
            return true;
        }

        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];

            if (newX >= 0 && newX < M && newY >= 0 && newY < N && !visited[newX][newY] && word.charAt(pos) == board[newX][newY]) {
                visited[newX][newY] = true;
                boolean res = dfs(board, newX, newY, visited, word, pos + 1);
                if (res) {
                    return true;
                }
                visited[newX][newY] = false;
            }
        }

        return false;
    }

    // If visited is outside of for loops
    public boolean exist(char[][] board, String word) {
        M = board.length;
		N = board[0].length;

        boolean[][] visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
                // check visited?
                if (board[i][j] == word.charAt(0)) {
                    
                    visited[i][j] = true;
                    boolean res = dfs(board, i, j, visited, word, 1);
                    if (res) {
                        return true;
                    }
                    // If visited is outside of the double for loop, then you need to cleanup every step.
                    // Change back to false on every step.
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }
}

public class WordSearch {

	public static void main(String[] args) {

	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int m = -1;
	int n = -1;
	
	// 给一个matrix和一个string，判断string在不在matrix中
	public boolean exist(char[][] board, String word) {
		Set<Integer> visited = new HashSet<Integer>();
		
		m = board.length;
		n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) { // 第一个char match
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
				  {'i','h','o','r'},
				  {'i','f','l','v'}
		};
		
		String[] words = {"oath","pea","eat","rain"};
		
		WordSearch2 ws2 = new WordSearch2();
//		List<String> res = ws2.findWords(board, words);
		List<List<int[]>> res = ws2.findWordsWithPath(board, "tao");
		
		for (List<int[]> list : res) {
			for(int[] pair : list) {
				System.out.println(pair[0] + " : " + pair[1]);	
			}
			System.out.println();
		}
	}
	
	/**
	 * 题目是二维的char图 和一个字符串 找到这个图里所有路径有这个字符串的
		譬如说
		# [
		#    a, v, z
		#    p, e, a
		#    t, e, p
		# ]
		
		和 ape
		
		输出就是 [[{0,0},{1,0},{1,1}], [{1,2},{2,2},{2,1}]]
		
		DFS更好写
	 * @return
	 */
	List<List<int[]>> findWordsWithPath(char[][] board, String word) {
    	m = board.length;
    	n = board[0].length;
    	
		List<List<int[]>> res = new ArrayList<List<int[]>>();
		List<int[]> list = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == word.charAt(0)) {
					visited.add(i * n + j);
					list.add(new int[]{i, j});
					helper(res, list, board, visited, word, 1, i, j);
					list.remove(list.size() - 1);
					visited.remove(i * n + j);
				}
			}
		}

		return res;
	}
	
	void helper(List<List<int[]>> res, List<int[]> list, char[][] board, Set<Integer> visited, String word, int pos, int i, int j) {
		if(pos == word.length()) {
			res.add(new ArrayList<int[]>(list));
			return;
		}

		for(int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			int newId = x * n + y;
			
			if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == word.charAt(pos) && !visited.contains(newId)) {
				visited.add(newId);
				list.add(new int[]{x, y});
				helper(res, list, board, visited, word, pos + 1, x, y);
				list.remove(list.size() - 1);
				visited.remove(newId);
			}
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
            // 不return，还可能又更长的string
        }
        
        if (root.children.containsKey(board[i][j])) {
            for (int[] dir : directions) { // 如果当前TrieNode中有当前的char, 那就尝试四个不同的方向，并且移动TrieNode
                int x = i + dir[0];
                int y = j + dir[1];
                int id = x * n + y;
                
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited.contains(id)) {
                    visited.add(id);
                    dfs(board, res, x, y, root.children.get(board[i][j]), visited);
                    visited.remove(id);
                }
            }
        } // 如果TrieNode中没有当前这个char就算了
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
            
            char key = word.charAt(pos);
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