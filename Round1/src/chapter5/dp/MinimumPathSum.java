package chapter5.dp;
/**
 * 64. Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 * @author Lei
 *
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] hash = new int[m][n];
        hash[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++) {
            hash[i][0] = hash[i - 1][0] + grid[i][0];
        }
        
        for (int i = 1; i < n; i++) {
            hash[0][i] = hash[0][i - 1] + grid[0][i];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                hash[i][j] = Math.min(hash[i - 1][j], hash[i][j - 1]) + grid[i][j];
            }
            
            // If want to find out the PATH, need to have an array to track steps
            /**
            for (int j = 1; j < n; j++) {
            	if (hash[i - 1][j] < hash[i][j - 1]) {
            		pre[i][j] = LEFT; // Where I am from
            		hash[i][j] = hash[i - 1][j] + grid[i][j];
            	} else {
            		pre[i][j] = TOP;
            		hash[i][j] = hash[i][j - 1] + grid[i][j];
            	}
            }
            
            At the end, track from the right-bottom corner to top-left, to find out the PATH
            **/
        }
        
        return hash[m - 1][n - 1];
    }
}
