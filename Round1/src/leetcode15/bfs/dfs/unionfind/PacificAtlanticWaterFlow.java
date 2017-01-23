package leetcode15.bfs.dfs.unionfind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 417. Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {
    // https://discuss.leetcode.com/topic/62379/java-bfs-dfs-from-ocean
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public List<int[]> pacificAtlanticBFS(int[][] matrix) {
        List<int[]> res = new ArrayList<int[]>();
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;    
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> pQueue = new LinkedList<int[]>();
        Queue<int[]> aQueue = new LinkedList<int[]>();
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        
        // First and last row
        for (int i = 0; i < n; i++) {
            pQueue.offer(new int[]{0, i});
            pVisited[0][i] = true;
            aQueue.offer(new int[]{m - 1, i});
            aVisited[m - 1][i] = true;
        }
        
        // First and last column
        for (int i = 0; i < m; i++) {
            pQueue.offer(new int[]{i, 0});
            pVisited[i][0] = true;
            aQueue.offer(new int[]{i, n - 1});
            aVisited[i][n - 1] = true;
        }
        
        bfs(matrix, pQueue, pVisited);
        bfs(matrix, aQueue, aVisited);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        
        return res;
    }
    
    void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            for (int[] dir : direction) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && matrix[newX][newY] >= matrix[x][y]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            } 
        }
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<int[]>();
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;    
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, pVisited, i, 0);
            dfs(matrix, aVisited, i, n - 1);
        }
        
        for (int i = 0; i < n; i++) {
            dfs(matrix, pVisited, 0, i);
            dfs(matrix, aVisited, m - 1, i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        
        return res;
    }
    
    void dfs(int[][] matrix, boolean[][] visited, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        visited[x][y] = true;
        
        for (int[] dir : direction) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && matrix[newX][newY] >= matrix[x][y]) {
                dfs(matrix, visited, newX, newY);
            }
        }
    }
}
