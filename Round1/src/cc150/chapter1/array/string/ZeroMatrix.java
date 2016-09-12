package cc150.chapter1.array.string;

public class ZeroMatrix {

	public static void main(String[] args) {
		ZeroMatrix zm = new ZeroMatrix();
		int[][] matrix = {{0, 2, 3}, {4, 0, 5}, {7, 8, 9}};
		int[][] res = zm.zeroMatrix(matrix);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " - ");
			}
			System.out.println();
		}
	}

	int[][] zeroMatrix(int[][] matrix) {
		boolean[] col = new boolean[matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			boolean zeroInRow = false;
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					zeroInRow = true;
					col[j] = true;
				}
			}
			
			if (zeroInRow) {
				for (int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		
		for (int j = 0; j < matrix[0].length; j++) {
			if (col[j]) {
				for (int i = 0; i < matrix.length; i++) {
					matrix[i][j] = 0;
				}
			}
		}
		
		return matrix;
	}
}
