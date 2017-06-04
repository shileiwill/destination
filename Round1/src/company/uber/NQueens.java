package company.uber;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (n < 1) {
            return res;
        }
        dfs(res, list, n);
        
        return res;
    }
    
    void dfs(List<List<String>> res, List<Integer> list, int n) {
        if (list.size() == n) {
            res.add(drawBoard(list, n));
            return;
        }
        
        for (int col = 0; col < n; col++) {// Check every column
            if (isValid(list, col)) {
                list.add(col);
                dfs(res, list, n);
                list.remove(list.size() - 1);
            }
        }
    }
    
    boolean isValid(List<Integer> list, int col) {
        int row = list.size(); // New Row
        
        for (int i = 0; i < list.size(); i++) {
            int curRow = i;
            int curCol = list.get(i);
            
            if (curCol == col || curRow + curCol == row + col || curRow - curCol == row - col) {
                return false;
            }
        }
        
        return true;
    }
    
    List<String> drawBoard(List<Integer> list, int n) {
        List<String> res = new ArrayList<String>();
        
        for (int i = 0; i < n; i++) { // All Rows
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) { // Each Col
                if (list.get(i) == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            
            res.add(sb.toString());
        }
        
        return res;
    }

}
