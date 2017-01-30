package leetcode1.array;
/**
 * 48. You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

1  2  3             
4  5  6
7  8  9
after transpose, it will be swap(matrix[i][j], matrix[j][i])

1  4  7
2  5  8
3  6  9
Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

7  4  1
8  5  2
9  6  3
 */
public class RotateImage {
	  public void rotate(int[][] matrix) {
	        int len = matrix.length; // this is both width and height
	       
	        // Swap [i][j], [j][i]
	        for (int i = 0; i < len; i++) { // i is row(height) // Row
	            for (int j = i + 1; j < len; j++) { // j is col(width). Make it only half the square // Col
	                int temp = matrix[i][j];
	                matrix[i][j] = matrix[j][i];
	                matrix[j][i] = temp;
	            }
	        }
	       
	        // Swap [i][j], [i][len - 1 - j]
	        for (int i = 0; i < len; i++) { // Each row
	            for (int j = 0; j < len / 2; j++) { // Only need half the cols
	                int temp = matrix[i][j];
	                matrix[i][j] = matrix[i][len - 1 - j];
	                matrix[i][len - 1 - j] = temp;
	            }
	        }
	    }
}
