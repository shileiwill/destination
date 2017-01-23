package leetcode14.topologicalsort;
/**
 * 329. Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInAMatrix {
    // https://discuss.leetcode.com/topic/34835/15ms-concise-java-solution
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] hash = new int[m][n];
        
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, hash, i, j);
                max = Math.max(max, len);
            }
        }
        
        return max;
    }
    
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int dfs(int[][] matrix, int[][] hash, int i, int j) {
        if (hash[i][j] != 0) {
            return hash[i][j];
        }
        
        int max = 1;
        
        for (int[] dir : directions) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            
            int len = 1 + dfs(matrix, hash, x, y);
            max = Math.max(max, len);
        }
        
        hash[i][j] = max;
        return max;
    }
}
