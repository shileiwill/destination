package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 * @author Lei
 *
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (n < 1) {
            return res;
        }
        
        search(res, list, n);
        
        return res;
    }
    
    // Transform List<Integer> to List<String>
    List<String> drawChessBoard(List<Integer> list, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            int pos = list.get(i);
            StringBuilder sb = new StringBuilder();
            
            for (int j = 0; j < n; j++) {
                if (j == pos) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            
            board.add(sb.toString());
        }
        
        return board;
    }
        
    boolean isValid(List<Integer> list, int col) {
        int row = list.size();
        for (int i = 0; i < list.size(); i++) {
            int pos = list.get(i);
            if (col == pos) { // Same Column
                return false;
            }
            
            if (col + row == i + pos) {
                return false;
            }
            
            if (col - row == pos - i) {
                return false;
            }
        }
        return true;
    }
        
    // Operating List<String> on the go is a pain, so we use List<Integer> instead. Will use drawChessBoard later.
    // list 记录Queens所在的位置
    void search(List<List<String>> res, List<Integer> list, int n) {
        if (list.size() == n) {
            res.add(drawChessBoard(list, n));
            return;
        }
        
        for (int i = 0; i < n; i++) { // Try every col. res.size() is recording the rows
            if (isValid(list, i)) {
                list.add(i);
                search(res, list, n);
                list.remove(list.size() - 1);
            }
        }
    }
}

class NQueens1 {
	static int n = 8;
	static int count = 0;
	static int[] solution = new int[n];
	static List<int[]> res = new ArrayList<int[]>();
	
	public static void main(String[] args) {
		dfs(0);
		System.out.println(count);
		System.out.println(res.size());
		
		for (int[] arr : res) {
			for (int val : arr) {
				System.out.print(val + " - ");
			}
			System.out.println();
		}
	}
	
	static void dfs(int row) {
		for (int col = 0; col < n; col++) { // Every column
			int i = 0;
			for (i = 0; i < row; i++) { // Check validity
				if (col == solution[i] || row - col == i - solution[i] || row + col == solution[i] + i) {
//					ok = false; // As long as one mistake, just break, false
					break;
				}
			}
			
			if (i < row) {
				continue; // This col doesnt work. Try next col
			}
			
			// Found a solution
			solution[row] = col;
			
			// Done?
			if (row == n - 1) {
				count++; // The end, one solution found.
				res.add(Arrays.copyOf(solution, n));// Can also save the solution here
			} else {
				dfs(row + 1); // Go to next row
			}
		}
	}
}

class NQueens2 {
	static int n = 8;
	static int count = 0;
	static boolean[] shu = new boolean[n];
	static boolean[] pie = new boolean[2 * n - 1];
	static boolean[] na = new boolean[2 * n -1];
	
	public static void main(String[] args) {
		dfs(0);
		System.out.println(count);
	}
	
	static void dfs(int row) {
		for (int col = 0; col < n; col++) {
			int j = row + col; // 唯一标示撇， 从0到6
			int k = col - row + n - 1; // 唯一表示捺  因为col - row is from -(n - 1) to 0 to (n - 1), so need to differentiate
			
			if (shu[col] || pie[j] || na[k]) {
				continue;
			}
			
			if (row == n - 1) {
				count++;
			} else {
				shu[col] = true; pie[j] = true; na[k] = true;
				dfs(row + 1);
				shu[col] = false; pie[j] = false; na[k] = false;
			}
		}
	}
}

class NQueens3 {
	static int n = 8;
	static int count = 0;
	static int shu, pie, na;
	
	public static void main(String[] args) {
		dfs(0);
		System.out.println(count);
	}
	
	static void dfs(int row) {
		for (int col = 0; col < n; col++) {
			int j = row + col; // 唯一标示撇， 从0到6
			int k = col - row + n - 1; // 唯一表示捺  因为col - row is from -(n - 1) to 0 to (n - 1), so need to differentiate
			
			if ((((shu >> col) | (pie >> j) | (na >> k)) & 1) != 0) { // See if there is any 1 in the 3 positions
				continue;
			}
			
			if (row == n - 1) {
				count++;
			} else {
				shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k); // Flip
				dfs(row + 1);
				shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k); // Flip back
			}
		}
	}
}