package leetcode4.hashtable;

import java.util.Arrays;
/*
 * 36. Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];
        
        // Row
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < board[i].length; j++) {
                char digit = board[i][j];
                if (ifExist(visited, digit)) {
                    return false;
                }
            }
        }
        
        // Col
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < board[i].length; j++) {
                char digit = board[j][i];
                if (ifExist(visited, digit)) {
                    return false;
                }
            }
        }
        
        // Square
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[i].length; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    char digit = board[i + k / 3][j + k % 3];
                    if (ifExist(visited, digit)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    boolean ifExist(boolean[] visited, char digit) {
        // Not even a number
        if (digit == '.') { 
            return false;
        }
        
        int num = digit - '0';
        
        // This number is visited already
        if (num < 1 || num > 9 || visited[num - 1]) {
            return true;
        }
        
        visited[num - 1] = true;
        return false;
    }
}
