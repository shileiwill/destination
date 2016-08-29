package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
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
            if (col == pos) {
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
        
        for (int i = 0; i < n; i++) {
            if (isValid(list, i)) {
                list.add(i);
                search(res, list, n);
                list.remove(list.size() - 1);
            }
        }
    }
}
