package company.uber;

import java.util.LinkedList;
import java.util.*;

/**
 * 286
 * You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */
public class WallAndGates {

	public static void main(String[] args) {

	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int m = 0;
	int n = 0;
	
	public void wallsAndGatesMultiEndBFS(int[][] rooms) {
		m = rooms.length;
		n = rooms[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {// Gate
					queue.offer(i * n + j);
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int i = now / n;
			int j = now % n;
			
			for (int[] dir : directions) {
				int x = i + dir[0];
				int y = j + dir[1];
				int id = x * n + y;
				
				if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
					rooms[x][y] = rooms[i][j] + 1;
					queue.offer(id);
				}
			}
		}
	}
	
	public void wallsAndGatesDFS(int[][] rooms) {
		m = rooms.length;
		n = rooms[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {// Gate
					dfs(rooms, i, j);
				}
			}
		}
	}
	
	void dfs(int[][] rooms, int i, int j) {
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			
			if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] > rooms[i][j]) {
				rooms[x][y] = rooms[i][j] + 1;
				dfs(rooms, x, y);
			}
		}
	}
}
