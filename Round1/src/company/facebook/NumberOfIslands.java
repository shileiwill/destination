package company.facebook;

import java.util.LinkedList;
import java.util.Queue;

// 200. Number of Islands
class Solution {

    int M = -1;
    int N = -1;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Solution 1: use DFS, change the matrix to avoid revisit
    // If you don't want to use the directions 2D array
    public int numIslands(char[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					count++;
					int[] area = {0}; // Area is 0 to start with
					// grid[i][j] = '0'; // Don't set 0 here!
					dfs(grid, i, j, area);
					System.out.println(area[0]);
				}
			}
		}
		
		return count;
	}
	
	void dfs(char[][] grid, int i, int j, int[] area) {
		// if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
		// 	return;
		// }
		// grid[i][j] = '0';
		// dfs(grid, i - 1, j);
		// dfs(grid, i + 1, j);
		// dfs(grid, i, j - 1);
		// dfs(grid, i, j + 1);

		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
			area[0] += 1;
			grid[i][j] = '0';
			dfs(grid, i - 1, j, area);
			dfs(grid, i + 1, j, area);
			dfs(grid, i, j - 1, area);
			dfs(grid, i, j + 1, area);
		}
	}

	// Solution 2: use directions 2D array
    public int numIslandsDFS(char[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					count++;
					int[] area = {1}; // Start from 1
					grid[i][j] = '0'; // Set 0 here
					dfs(grid, i, j, area);
					System.out.println("This area of this island : " + area[0]);
				}
			}
		}
		
		return count;
	}
	
	void dfs(char[][] grid, int i, int j, int[] area) {
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

    // Solution 3: Use DFS, use visited matrix
    // modify the matrix if you dont use the visited matrix
    void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;

        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];

            if (newX >= 0 && newX < M && newY >= 0 && newY < N && !visited[newX][newY] && grid[newX][newY] == '1') {
            	// area[0] = area[0] + 1;
                dfs(grid, visited, newX, newY);
            }
        }
    }


    // Solution 4: Use BFS
    public int numIslands(char[][] grid) {
        M = grid.length;
        N = grid[0].length;

        boolean[][] visited = new boolean[M][N];

        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                	// if we need to calculate the size of the island, pass in an area = {1} to bfs/dfs
                    bfs(grid, visited, i, j);
                    // dfs(grid, visited, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    void bfs(char[][] grid, boolean[][] visited, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int[] dir : directions) {
                int newX = now[0] + dir[0];
                int newY = now[1] + dir[1];

                if (newX >= 0 && newX < M && newY >= 0 && newY < N && !visited[newX][newY] && grid[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }

        }
    }
}