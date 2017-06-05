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
		Queue<Integer> queue = new LinkedList<Integer>(); // Better to use int[]
		
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

/**
 * 给一个字符矩阵，里面只能forward slash '\' 或者backslash '/'. 
 * \ \ / / 
 * / / / \ 
 * 把这些slash对应链接起来就对应一个图（undirect graph）. 然后需要用dfs或者bfs的搜索路径。
 */
class SlashGraph {
	public static void main(String[] args) {
		char[][] matrix = {{'\\', '\\', '/', '/'}, {'/', '/', '/', '\\'}, {'\\', '\\', '\\', '/'}, {'\\', '/', '/', '/'}};
		int[] from = {0, 0};
		int[] to = {3, 3};
		
		SlashGraph sg = new SlashGraph();
		sg.prepare();
		boolean res = sg.findPath(matrix, from, to);
		System.out.println(res);
	}
	
	Map<Character, int[][]> map = new HashMap<Character, int[][]>();
	int m = 0;
	int n = 0;
	
	void prepare() {
		int[][] arr1 = {{-1, -1}, {1, 1}};
		int[][] arr2 = {{-1, 1}, {1, -1}};
		
		map.put('\\', arr1);
		map.put('/', arr2);
	}
	
	boolean findPath(char[][] matrix, int[] from, int[] to) {
		m = matrix.length;
		n = matrix[0].length;
		
		int id1 = from[0] * n + from[1];
		int id2 = to[0] * n + to[1];
		
		if (id1 == id2) {
			return true;
		}
		
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(id1);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(id1);
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / n;
			int y = now % n;
			
			int[][] directions = map.get(matrix[x][y]);
			
			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];
				int newId = newX * n + newY;
				
				if (newId == id2) {
					return true;
				}
				
				if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited.contains(newId)) {
					visited.add(newId);
					queue.offer(newId);
				}
			}
		}
		
		return false;
	}
}