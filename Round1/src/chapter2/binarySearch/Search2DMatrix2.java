package chapter2.binarySearch;
/**
 * 240. Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 * @author Lei
 *
 */
public class Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Find the maximum row first
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
        
        int start = 0; // This start helps performance, as it skips the elements on left
        // Find target in rows above [row]
        for (int aRow = row; aRow >= 0; aRow--) {
            int left = start;
            int right = matrix[aRow].length - 1;
            
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                
                if (target == matrix[aRow][mid]) {
                    return true;
                } else if (target < matrix[aRow][mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            
            if (target == matrix[aRow][left] || target == matrix[aRow][right]) {
                return true;
            }
            
            // If target is smaller than the left-top corner, or, larger than the right-bottom corner
            if (target < matrix[aRow][left] || target > matrix[aRow][right]) {
                return false;
            }
            
            start = left;
        }
        
        return false;
    }
    
    // My new version
    public boolean searchMatrix2(int[][] matrix, int target) {
        int top = 0;
        int bottom = matrix.length - 1;
        
        while (top + 1 < bottom) {
            int mid = top + (bottom - top) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (target < matrix[mid][0]) {
                bottom = mid;
            } else {
                top = mid;
            }
        }
        
        if (target == matrix[top][0] || target == matrix[bottom][0]) {
            return true;
        }
        if (target < matrix[top][0]) {
            return false;
        }
        
        if (target < matrix[bottom][0]) {
            bottom = top;
        }
        top = 0;
        int right = matrix[top].length - 1;
        
        for (int i = top; i <= bottom; i++) {
            int left = 0;
            
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                }
                if (matrix[i][mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            
            if (matrix[i][left] == target || matrix[i][right] == target) {
                return true;
            }
            if (matrix[i][right] > target) {
                right = left;
            }
        }
        
        return false;
    }
}
