package company.snapchat;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return res;
        }
        
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Left to right on row
            for (int i = colStart; i <= colEnd; i++) {
                res.add(matrix[rowStart][i]);
            }
            rowStart++;
            
            // Top to bottom on col
            for (int i = rowStart; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            // Right to left on bottom row
            if (rowEnd >= rowStart) { // As rowStart is increasing, check if still need to
                for (int i = colEnd; i >= colStart; i--) {
                    res.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            
            // Bottom to top on left col
            if (colEnd >= colStart) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    res.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }
        
        return res;
    }
    
    /**
     * 59. Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

		For example,
		Given n = 3,
		
		You should return the following matrix:
		[
		 [ 1, 2, 3 ],
		 [ 8, 9, 4 ],
		 [ 7, 6, 5 ]
		]
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        
        int val = 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Left to right on row
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = val++;
            }
            rowStart++;
            
            // Top to bottom on col
            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = val++;
            }
            colEnd--;
            
            // Right to left on bottom row
            if (rowEnd >= rowStart) { // As rowStart is increasing, check if still need to
                for (int i = colEnd; i >= colStart; i--) {
                    matrix[rowEnd][i] = val++;
                }
                rowEnd--;
            }
            
            // Bottom to top on left col
            if (colEnd >= colStart) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = val++;
                }
                colStart++;
            }
        }
        
        return matrix;
    }
}
