package company.jet.com;

public class DiagonalDiff {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4};
		int[] b = {1, 2, 3, 4};
		int[] c = {1, 2, 3, 4};
		int[] d = {11, 12, 13, 4};
		
		int[][] arr = {a, b, c, d};
		
		diff(arr);
	}

	static void diff(int[][] matrix) {
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (i == j) {
					sum1 += matrix[i][j];
				}
				
				if (i + j == matrix.length - 1) {
					sum2 += matrix[i][j];
				}
			}
		}
		
		System.out.println(sum1 + " : " + sum2);
	}
}
