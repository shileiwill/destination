package leetcode15.bfs.dfs.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */
public class NumberOfIslands {
    public int numIslandsDFS(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
    
    public int numIslandsBFS(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
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
        queue.offer(i * n + j); // Why have to use column number
        grid[i][j] = '0'; // Visited
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / n;
            int y = cur % n;
            // System.out.println(cur + "---" + x + "---" + y);
            
            if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                queue.offer((x - 1) * n + y);
                grid[x - 1][y] = '0'; // It will be faster if we set to 0 immediately
            }
            
            if (x + 1 < m && grid[x + 1][y] == '1') {
                queue.offer((x + 1) * n + y);
                grid[x + 1][y] = '0';
            }
            
            if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                queue.offer(x * n + (y - 1));
                grid[x][y - 1] = '0';
            }
            
            if (y + 1 < n && grid[x][y + 1] == '1') {
                queue.offer(x * n + (y + 1));
                grid[x][y + 1] = '0';
            }
        }
    }
    
    // https://discuss.leetcode.com/topic/39980/1d-union-find-java-solution-easily-generalized-to-other-problems
    // http://www.tuicool.com/articles/ria6Bbe
    int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int numIslandsUnionFind(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        UnionFind uf = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : direction) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                    
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            int id1 = i * n + j;
                            int id2 = x * n + y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }
        
        return uf.count;
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
        
        void union(int id1, int id2) {
            int parent1 = find(id1);
            int parent2 = find(id2);
            
            if (parent1 != parent2) {
                count--; // they should be together
                parent[parent1] = parent2; // Share the same ancestor now
            }
        }
        
        int find(int id) {
            if (parent[id] == id) {
                return id;
            }
            
//            parent[id] = find(parent[id]); // Recursion to find the ancestor
//            return parent[id]; // return the ancestor
            return parent[id]; // This is good enough
        }
    }
}