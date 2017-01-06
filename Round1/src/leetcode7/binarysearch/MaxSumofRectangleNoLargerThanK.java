package leetcode7.binarysearch;

import java.util.TreeSet;

/**
 * 363. Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */
public class MaxSumofRectangleNoLargerThanK {
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;    
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        // Find max subarray with sum <= k in 1D array
        int[][] sum = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = matrix[i][j];
                if (i - 1 >= 0) {
                    sum[i][j] += sum[i - 1][j];
                }
                if (j - 1 >= 0) {
                    sum[i][j] += sum[i][j - 1];
                }
                
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum[i][j] -= sum[i - 1][j - 1];
                }
            }
        }
        
        int res = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < m; r1++) {
            for (int c1 = 0; c1 < n; c1++) { // Start point
                for (int r2 = r1; r2 < m; r2++) {
                    for (int c2 = c1; c2 < n; c2++) { // End point
                        int cur = sum[r2][c2];
                        
                        if (r1 - 1 >= 0) {
                            cur -= sum[r1 - 1][c2];
                        }
                        if (c1 - 1 >= 0) {
                            cur -= sum[r2][c1 - 1];
                        }
                        if (r1 - 1 >= 0 && c1 - 1 >= 0) {
                            cur += sum[r1 - 1][c1 - 1];
                        }
                        
                        if (cur <= k) {
                            res = Math.max(res, cur);
                        }
                    }
                }
            }
        }
        
        return res;
    }
    
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;    
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        // Find max subarray with sum <= k in 1D array
        int[][] sum = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = matrix[i][j];
                if (i - 1 >= 0) {
                    sum[i][j] += sum[i - 1][j];
                }
                if (j - 1 >= 0) {
                    sum[i][j] += sum[i][j - 1];
                }
                
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum[i][j] -= sum[i - 1][j - 1];
                }
            }
        }
        
        int res = Integer.MIN_VALUE;
        
        for (int r1 = 0; r1 < m; r1++) {
            for (int r2 = r1; r2 < m; r2++) {
                TreeSet<Integer> tree = new TreeSet<Integer>();
                tree.add(0); // Padding
                for (int c = 0; c < n; c++) {
                    int cur = sum[r2][c];
                    if (r1 - 1 >= 0) {
                        cur -= sum[r1 - 1][c];
                    }
                    // The least greatest element which is greater or equal to (cur - k)
                    Integer ceil = tree.ceiling(cur - k);
                    if (ceil != null) {
                        res = Math.max(res, cur - ceil);
                    }
                    tree.add(cur);
                }
            }
        }
        
        return res;
    }
    /*
    To get area of a rectangle, i use a large area to subtract a smaller area on the left.
we add a padding area of 0 to enable the case where the large area is the rectangle without a smaller area on the left to subtract; that is, the rectangle's left column is column 0.
    */
}
