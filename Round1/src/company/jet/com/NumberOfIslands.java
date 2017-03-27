package company.jet.com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfIslands {

	public static void main(String[] args) {
		int[][] grid = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,1,0,0}, {0,0,0,1,1}};
		NumberOfIslands noi = new NumberOfIslands();
		List<int[]> res = noi.islands(grid);
		
		for (int[] val : res) {
			System.out.println(val[0] + "========" + val[1]);
		}
//		1,1,0,0,0
//		1,1,0,0,0
//		0,0,1,0,0
//		0,0,0,1,1
	}

	List<int[]> islands(int[][] grid) {
		List<int[]> res = new ArrayList<int[]>();
		int m = grid.length;
		int n = grid[0].length;
		
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					count++; // Number of islands
					bfs(grid, i, j, res); // Get the index of islands
				}
			}
		}
		
		System.out.println("Number of islands is : " + count);
		return res;
	}
	
	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	void bfs(int[][] grid, int i, int j, List<int[]> res) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[]{i, j});
		grid[i][j] = 2; // visited
		res.add(new int[]{i, j});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			
			for (int[] dir : direction) {
				int x2 = x + dir[0];
				int y2 = y + dir[1];
				
				if (x2 >= 0 && x2 < grid.length && y2 >= 0 && y2 < grid[0].length && grid[x2][y2] == 0) {
					queue.offer(new int[]{x2, y2});
					grid[x2][y2] = 2; // visited
					res.add(new int[]{x2, y2});
				}
			}
		}
	}
}
