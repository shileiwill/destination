package company.yahoo;

public class KSum {

	public static void main(String[] args) {

	}

	// Get count of solution
	int kSum(int[] arr, int k, int target) {
		int[][][] hash = new int[arr.length + 1][k + 1][target + 1];
		
		for (int i = 0; i <= arr.length; i++) {
			for (int j = 0; j <= k; j++) {
				for (int t = 0; t <= target; t++) {
					if (j == 0 && t == 0) {
						hash[i][j][t] = 1;
					} else if (i != 0 && j != 0 && t != 0) {
						hash[i][j][t] = hash[i - 1][j][t];
						if (t - arr[i - 1] >= 0) {
							hash[i][j][t] += hash[i - 1][j - 1][t - arr[i - 1]];
						}
					}
				}
			}
		}
		
		return hash[arr.length][k][target];
	}
}
