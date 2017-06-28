package company.facebook;
/**
 * matrix每一行都是00111这一类的，只要出现1，后面全是1，找整个最左边的1
 */
public class BinarySearchMatrix {

	public static void main(String[] args) {
		int[][] M = {{0, 0, 0, 1, 1},
					 {0, 0, 1, 1, 1},
					 {0, 0, 0, 0, 1},
					 {0, 0, 1, 1, 1}};
		int res = find(M);
		System.out.println(res);
	}

	static int find(int[][] M) {
		int m = M.length;
		int n = M[0].length;
		int res = n - 1;
		
		for (int i = 0; i < m; i++) {
			int left = 0;
			int right = res;
			
			while (left + 1 < right) {
				int mid = left + (right - left) / 2;
				
				if (M[i][mid] == 1) {
					right = mid;
				} else {
					left = mid;
				}
			}
			
			if (M[i][left] == 1) {
				res = Math.min(res, left);
			} else if (M[i][right] == 1) {
				res = Math.min(res, right);
			} else {
				// This section is all 0s, ignore
			}
		}
		
		return res;
	}
}
