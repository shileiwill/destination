package company.uber;

import java.util.Arrays;

public class SudokuSolver {

	public static void main(String[] args) {

	}

	boolean isValidSudoku(char[][] board) {
		boolean[] visited = new boolean[9];
		
		for (int i = 0; i < 9; i++) { // Each row
			Arrays.fill(visited, false);
			for (int j = 0; j < 9; j++) {
				char now = board[i][j];
				
				if (now == '.' ) {
					
				} else {
					if (visited[now - '1']) {
						return false;
					}
					visited[now - '1'] = true;
				}
			}
		}
		
		for (int j = 0; j < 9; j++) { // Each col
			Arrays.fill(visited, false);
			for (int i = 0; i < 9; i++) {
				char now = board[i][j];
				
				if (now == '.' ) {
					
				} else {
					if (visited[now - '1']) {
						return false;
					}
					visited[now - '1'] = true;
				}
			}
		}
		
		for (int i = 0; i < 9; i += 3) { // Each square
			for (int j = 0; j < 9; j += 3) {
				Arrays.fill(visited, false);
				for (int k = 0; k < 9; k++) {
					char now = board[i + k / 3][j + k % 3]; // This is the most important part
					
					if (now == '.' ) {
						
					} else {
						if (visited[now - '1']) {
							return false;
						}
						visited[now - '1'] = true;
					}
				}
			}
		}
		
		return true;
	}
	
	public void solveSudoku(char[][] board) {
		solve(board);
	}
	
	boolean solve(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {// Need to fill this guy
					for (char c = '1'; c <= '9'; c++) { // We can shuffle the array to provide different solutions
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							
							if (solve(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}
					}
					return false; // Not able to fill this spot
				}
			}
		}
		
		return true;
	}
	
	boolean isValid(char[][] board, int i, int j, char c) {
		for (int x = 0; x < 9; x++) {
			if (board[x][j] == c) { // Each row
				return false;
			}
			
			if (board[i][x] == c) { // Each col
				return false;
			}
			
			if (board[3 * (i / 3) + x / 3][3 * (j / 3) + x % 3] == c) { // Each square
				return false;
			}
		}
		
		return true;
	}
}
