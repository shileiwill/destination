package company.jet.com;

import java.util.HashSet;
import java.util.Set;

/**
 *  1 1 1 1 1 1 
	1 1 1 1 1 1 
	1 1 0 0 0 0 
	1 1 0 0 0 0
 */
public class Rectangle2 {

	public static void main(String[] args) {
		int[][] matrix = { {1,1,1,1,1,1}, {1,0,0,0,0,1}, {1,0,0,0,0,1}, {1,1,1,1,1,1}, {1,1,1,1,0,0} };
		
		Rectangle2 rect = new Rectangle2();
		rect.findMultipleRectangle(matrix);
	}

	void findMultipleRectangle(int[][] M) {
		Set<Integer> visited = new HashSet<Integer>();
		int m = M.length;
		int n = M[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int val = M[i][j];
				int id = i * n + j;
				
				if (val == 0 && !visited.contains(id)) { // Found 1 left top corner
//					visited.add(id);
					int[] pair = dfs(M, i, j, visited);
					System.out.println(pair[0] + " : " + pair[1] + " : " + pair[2] + " : " + pair[3]);
				}
			}
		}
	}
	
	int[] dfs(int[][] M, int x, int y, Set<Integer> visited) {
		int row1 = x;
		int col1 = y;
		int row2 = x;
		int col2 = y;
		int m = M.length;
		int n = M[0].length;
		
		for (int i = x; i < m; i++) {
			if (M[i][y] != 0) {
				row2 = i - 1;
				break;
			} else if (i == m - 1) {
				row2 = m - 1;
			}
			
			for (int j = y; j < n; j++) {
				if (M[x][j] != 0) {
					col2 = j - 1;
					break;
				} else if (j == n - 1) {
					col2 = n - 1;
				}
				
				int id = i * n + j;
				visited.add(id);
			}
		}
		
		return new int[]{row1, col1, row2, col2};
	}
	
	void findSingleRectangle(int[][] M) {
		
		int[] point1 = findPoint1(M);
		int[] point2 = findPoint2(M);
		
		System.out.println(point1[0] + " : " + point1[1] + " : " + point2[0] + " : " + point2[1]);
	}
	
	int[] findPoint1(int[][] M) {
		int[] point1 = new int[2];
		int m = M.length;
		int n = M[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 0) { // Found!
					point1[0] = i;
					point1[1] = j;
					return point1;
				}
			}
		}
		
		return point1;
	}
	
	int[] findPoint2(int[][] M) {
		int[] point2 = new int[2];
		int m = M.length;
		int n = M[0].length;
		
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (M[i][j] == 0) { // Found!
					point2[0] = i;
					point2[1] = j;
					return point2;
				}
			}
		}
		
		return point2;
	}
}
