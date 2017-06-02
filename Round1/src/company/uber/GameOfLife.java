package company.uber;
/**
 * 289. According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton 
 * devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. 
In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
// Dont need queue, dont need visited
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        if (board.length == 0) {
            return;
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int lives = 0;
                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && (board[newX][newY] == 1 || board[newX][newY] == 2)) {
                        lives++;
                    }
                }
                // Space Complexity is O(1). Change to different numbers which are meaningful to you
                if ((board[i][j] == 0) && lives == 3) {
                    board[i][j] = 3; // Go live
                } else if ((board[i][j] == 1)) {
                    if (lives < 2 || lives > 3) {
                        board[i][j] = 2; // Go die. So status 2 actually was live 1
                    }
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (board[i][j] % 2);
            }
        }
    }
}
