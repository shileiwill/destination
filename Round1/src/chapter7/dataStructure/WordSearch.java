package chapter7.dataStructure;
/**
 * 79. Given a 2D board and a word, find if the word exists in the grid. 
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once. 

For example, 
Given board = 

[ 
     ["ABCE"], 
     ["SFCS"], 
     ["ADEE"] 
] 
word = "ABCCED", -> returns true, 
word = "SEE", -> returns true, 
word = "ABCB", -> returns false.
 * @author Lei
 *
 */
public class WordSearch {
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
}
