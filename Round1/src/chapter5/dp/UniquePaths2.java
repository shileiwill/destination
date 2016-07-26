package chapter5.dp;
/**
 * 63. Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
 * @author Lei
 *
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[][] hash = new int[m][n];
        
        boolean leftBlock = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                leftBlock = true;
            }
            hash[i][0] = leftBlock ? 0 : 1;
        }
        
        boolean topBlock = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                topBlock = true;
            }
            hash[0][i] = topBlock ? 0 : 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    hash[i][j] = 0;
                } else {
                    hash[i][j] = hash[i - 1][j] + hash[i][j - 1];
                }
            }
        }
        
        return hash[m - 1][n - 1];
    }
}
