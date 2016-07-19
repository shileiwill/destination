package chapter2.binarySearch.sortedArrays;
/**
 * 74. Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 * @author Lei
 *
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // The first column: matrix[i][0]
    	
    	// First find out which row it could be.
        int top = 0;
        int bottom = matrix.length - 1;
        
        while (top + 1 < bottom) {
            int mid = top + (bottom - top) / 2;
            
            if (target == matrix[mid][0]) {
                return true;
            } else if (target > matrix[mid][0]) {
                top = mid;
            } else {
                bottom = mid;
            }
        }
        
        if (target == matrix[top][0]) {
            return true;
        }
        if (target == matrix[bottom][0]) {
            return true;
        }
        
        int row = top;
        if (target > matrix[bottom][0]) {
            row = bottom;
        }
        
        // Search the row
        int left = 0;
        int right = matrix[row].length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (target == matrix[row][mid]) {
                return true;
            } else if (target < matrix[row][mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (target == matrix[row][left]) {
            return true;
        }
        if (target == matrix[row][right]) {
            return true;
        }
        
        return false;
    }
}
