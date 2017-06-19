package company.amazon.new_03_16;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 这个Follow up是经典 number of distinct islands
比之前的明显要难些。 需要用到hashing得思想。 
每一个岛将遍历完的点id(每个cell 可以分配一个id, id = i*m+j) 组合起来， 返回字符串，比如 “1/2/3/5”  这个岛有四个点。如果另一个岛是 "11/12/13/15"  只要把它offset下， 第一位归1， 它也变成"1/2/3/5"， 所以这2个岛的shape是一样的。 将这些第一位归1的字符串往set里丢。自然就除重了
中心思想： 将CELL ID组合来表示一个岛(hash to string)，然后变形string, 最后往set里丢。 done
 */
public class NumberOfIslands {

	public static void main(String[] args) {

	}

	// 如果要计算最大的岛的面积，每个cube是一平方米
	int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public int numIslandsDFS(char[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					count++;
					int[] area = {1};
					dfs(grid, i, j, area);
					System.out.println("This area of this island : " + area[0]);
				}
			}
		}
		
		return count;
	}
	
	void dfs(char[][] grid, int i, int j, int[] area) {
		grid[i][j] = '0';
		
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
				grid[x][y] = '0';
				area[0] = area[0] + 1;
				dfs(grid, x, y, area);
			}
		}
	}
	
	public int numIslandsBFS(char[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					count++;
					grid[i][j] = '0';
					bfs(grid, i, j);
				}
			}
		}
		
		return count;
	}
	
	void bfs(char[][] grid, int i, int j) {
		int m = grid.length;
		int n = grid[0].length;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		int id = i * n + j;
		queue.offer(id);
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			int x = now / n;
			int y = now % n;
			
			for (int[] dir : directions) {
				int x1 = x + dir[0];
				int y1 = y + dir[1];
				int id1 = x1 * n + y1;
				
				if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length && grid[x1][y1] == '1') {
					grid[x1][y1] = '0';
					queue.offer(id1);
				}
			}
		}
	}
	
	public int numIslandsUnionFind(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		UnionFind uf = new UnionFind(grid);
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '0') {
					continue;
				}
				int id = i * n + j;
				for (int[] dir : directions) {
					int x1 = i + dir[0];
					int y1 = j + dir[1];
					int id1 = x1 * n + y1;
					
					if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length && grid[x1][y1] == '1') {
						int p1 = uf.find(id);
						int p2 = uf.find(id1);
						
						if (p1 != p2) {
							uf.union(p1, p2);
						}
					}
				}
			}
		}
		
		return uf.count;
	}
}

class UnionFind {
	int[] parent = null;
	int count = 0;
	
	UnionFind(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		parent = new int[m * n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					count++;
					int id = i * n + j;
					parent[id] = id;
				}
			}
		}
	}
	
	int find(int id) {
		while (parent[id] != id) {
			id = parent[parent[id]];
		}
		return id;
	}
	
	void union(int id1, int id2) {
		int p1 = find(id1);
		int p2 = find(id2);
		
		if (p1 != p2) {
			count--;
			parent[p1] = p2;
		}
	}
}
