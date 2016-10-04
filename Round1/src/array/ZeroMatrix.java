package array;
/**
 * 73. Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */
public class ZeroMatrix {

	public static void main(String[] args) {
		ZeroMatrix zm = new ZeroMatrix();
		int[][] matrix = {{3,5,5,6,9,1,4,5,0,5},{2,7,9,5,9,5,4,9,6,8},{6,0,7,8,1,0,1,6,8,1},{7,2,6,5,8,5,6,5,0,6},{2,3,3,1,0,4,6,5,3,5},{5,9,7,3,8,8,5,1,4,3},{2,4,7,9,9,8,4,7,3,7},{3,5,2,8,8,2,2,4,9,8}};
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " - ");
			}
			System.out.println();
		}
		System.out.println();
		zm.setZeroes(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " - ");
			}
			System.out.println();
		}
	}

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean firstRow = false;
        boolean firstCol = false;
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                	System.out.println(i + " ---- " + j);
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
//                    break; // Cant break, because there may be more zeros on the same row, which will affect the column
                }
            }
        }
        
        // Rows
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                // Set entire row
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
            
        }
        
        // Cols
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                // Set entire col
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
            
        }
        
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRow) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }
    
    public void setZeroes2(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean[] colZero = new boolean[matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            boolean rowZero = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowZero = true;
                    colZero[j] = true;
                }
            }
            
            if (rowZero) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        for (int j = 0; j < matrix[0].length; j++) {
            if (colZero[j]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
