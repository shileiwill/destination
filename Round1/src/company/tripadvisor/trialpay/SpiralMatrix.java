package company.tripadvisor.trialpay;

public class SpiralMatrix {

	public static void main(String[] args) {

	}

	String getSpiralMatrix(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		int rowStart = 0;
		int rowEnd = matrix.length - 1;
		int colStart = 0;
		int colEnd = matrix[0].length - 1;
		
		while (rowStart <= rowEnd && colStart <= colEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				sb.append(matrix[rowStart][i]);
			}
			rowStart++;
			
			for (int i = rowStart; i <= rowEnd; i++) {
				sb.append(matrix[i][colEnd]);
			}
			colEnd--;
			
			if (rowEnd >= rowStart) {
				for (int i = colEnd; i >= colStart; i--) {
					sb.append(matrix[rowEnd][i]);
				}
				rowEnd--;
			}
			
			if (colEnd >= colStart) {
				for (int i = rowEnd; i >= rowStart; i--) {
					sb.append(matrix[i][colStart]);
				}
				colStart++;
			}
		}
		
		return sb.toString();
	}
}
