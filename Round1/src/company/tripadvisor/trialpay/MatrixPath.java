package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MatrixPath {

	public static void main(String[] args) {
		MatrixPath mp = new MatrixPath();
		
		boolean[][] matrix = {{false, true, true, true, false}, {false, false, false, false, true}, {true, true, true, false, false}, {true, false, false, false, true}};
		List<int[]> res = mp.findPath(matrix, new int[]{1, 2});
		
		for (int[] val : res) {
			System.out.println(val[0] + "===" + val[1]);
		}
	}

	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int m = -1;
	int n = -1;
	
	List<int[]> findPath(boolean[][] matrix, int[] start) {
		List<int[]> res = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();
		m = matrix.length;
		n = matrix[0].length;
		
		if (matrix[start[0]][start[1]]) {
			return res; // No way to go out
		}
		
		visited.add(start[0] * n + start[1]);
		dfs(matrix, start, res, visited);
		
		return res;
	}
	
	void dfs(boolean[][] matrix, int[] start, List<int[]> res, Set<Integer> visited) {
		int x = start[0];
		int y = start[1];
		
		if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
			res.add(start);
		}
		
		for (int[] dir : direction) {
			int x1 = x + dir[0];
			int y1 = y + dir[1];
			int id1 = x1 * n + y1;
			int[] newStart = new int[]{x1, y1};
			
			
			if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && !visited.contains(id1) && !matrix[x1][y1]) {
				visited.add(id1);
				dfs(matrix, newStart, res, visited);
				//visited.remove(id1); // Never go back
			}
		}
	}
	
	List<int[]> findPathBFS(boolean[][] matrix, int[] start) {
		List<int[]> res = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();
		m = matrix.length;
		n = matrix[0].length;
		
		if (matrix[start[0]][start[1]]) {
			return res; // No way to go out
		}
		
		visited.add(start[0] * n + start[1]);
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			int x = now[0];
			int y = now[1];
			
			if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
				res.add(now);
			}
			
			for (int[] dir : direction) {
				int x1 = x + dir[0];
				int y1 = y + dir[1];
				int id1 = x1 * n + y1;
				int[] newStart = new int[]{x1, y1};
				
				
				if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && !visited.contains(id1) && !matrix[x1][y1]) {
					visited.add(id1);
					queue.offer(newStart);
				}
			}
		}
		
		return res;
	}
}
