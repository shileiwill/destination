package chapter5.dp;
/**
 * 221. Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
 * @author Lei
 *
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        
        int[][] hash = new int[m][n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            hash[i][0] = matrix[i][0] - '0';
            max = Math.max(max, hash[i][0]); // 初始化的时候就放上max, 判断是否有1
        }
        for (int i = 0; i < n; i++) {
            hash[0][i] = matrix[0][i] - '0';
            max = Math.max(max, hash[0][i]);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    hash[i][j] = Math.min(Math.min(hash[i - 1][j], hash[i][j - 1]), hash[i - 1][j - 1]) + 1; 
                } else {
                    hash[i][j] = 0;
                }
                max = Math.max(max, hash[i][j]);
            }
        }
        
        return max * max;
    }
}
