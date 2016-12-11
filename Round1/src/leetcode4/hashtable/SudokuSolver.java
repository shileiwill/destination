package leetcode4.hashtable;
/**
 * 37. Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            
                            if (solve(board)) { // Move on. this is why we need the return type, boolean 范围逐步变小
                                return true;
                            } else {
                                board[i][j] = '.'; //Backtrack. 回退
                            }
                        }
                    }
                    
                    return false; // 1到9都遍历完了，没有return true, 这里就得false了
                }
            }
        }
        
        return true;
    }
    
    boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            // Check every Row on same column j. 万一任意别的cell有这个char, 就false
            if (board[k][j] == c) {
                return false;
            }
            // Check every Col on same row i
            if (board[i][k] == c) {
                return false;
            }
            // Square. First find the square, how to define it
            if (board[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] == c) {
                return false;
            }
        }
        
        return true;
    }
}