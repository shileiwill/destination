package company.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * 329. Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInMatrix {

	public static void main(String[] args) {

	}
	
	public int longestIncreasingPath(int[][] matrix) {
		int max = 0;
		
		Integer[][] hash = new Integer[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) { // Each point can be a start point
				int res = dfs(matrix, hash, i, j);
				max = Math.max(res, max);
			}
		}
		
		return max;
	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	int last = -1;
	
	private int dfs(int[][] matrix, Integer[][] hash, int i, int j) { // 不用visited check, 因为递增的肯定不会变小
		if (hash[i][j] != null) {
			return hash[i][j];
		}
		
		int max = 1; // Default is itself
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			
			if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
				map.put(matrix[x][y], matrix[i][j]); // 怎么得到路径？
				int len = 1 + dfs(matrix, hash, x, y); // Increase by 1 each time
				
				if (len > max) {
					last = matrix[x][y];
				}
				max = Math.max(max, len);
			}
		}
		
		hash[i][j] = max;
		return max;
	}
}
