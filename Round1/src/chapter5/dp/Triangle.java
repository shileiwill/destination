package chapter5.dp;

import java.util.List;
/**
 * 120. Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * @author Lei
 *
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] hash = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            hash[n - 1][i] = triangle.get(n - 1).get(i);
        }
        // From bottom to top is easy, as there is only 1 top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                hash[i][j] = Math.min(hash[i + 1][j], hash[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        
        return hash[0][0];
    }
    
	// This is Divide and Conquer, using recursion. Dynamic Programming will use only FOR loop
    public int minimumTotalDC(List<List<Integer>> triangle) {
        int[][] hash = new int[triangle.size()][triangle.size()];
        for (int i = 0; i < hash.length; i++) {
            for (int j = 0; j < hash[i].length; j++) {
                hash[i][j] = Integer.MIN_VALUE;
            }
        }
        return helper(triangle, 0, 0, hash);
    }
    
    int helper(List<List<Integer>> triangle, int x, int y, int[][] hash) {
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }
        
        if (hash[x][y] != Integer.MIN_VALUE) {
            return hash[x][y];
        }
        // Take care only of current level, which is x, y.
        hash[x][y] = Math.min(helper(triangle, x + 1, y, hash), helper(triangle, x + 1, y + 1, hash)) + triangle.get(x).get(y);
        
        return hash[x][y];
    }
    
    // Traditional Depth first traversal
    int min = Integer.MAX_VALUE;
    public int minimumTotal(int[][] triangle) {
        dfsHelper(triangle, 0, 0, 0);
        return min;
    }
    
    void dfsHelper(int[][] triangle, int x, int y, int curBest) {
        if (x + 1 == triangle.length) { // the end
            min = Math.min(min, curBest + triangle[x][y]);
            return;
        }
        
        dfsHelper(triangle, x + 1, y, curBest + triangle[x][y]);
        dfsHelper(triangle, x + 1, y + 1, curBest + triangle[x][y]);
    }
}
