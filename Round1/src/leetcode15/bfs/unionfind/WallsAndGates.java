package leetcode15.bfs.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. You are given a m x n 2D grid initialized with these three possible values.

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
public class WallsAndGates {
    // https://discuss.leetcode.com/topic/35242/benchmarks-of-dfs-and-bfs
    int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public void wallsAndGatesMultiEndBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) { // A gate
                    queue.offer(i * n + j);
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int id = queue.poll();
            int x = id / n;
            int y = id % n;
            
            for (int[] dir : direction) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                int newId = newX * n + newY;
                
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] == Integer.MAX_VALUE) {
                    rooms[newX][newY] = rooms[x][y] + 1;
                    queue.offer(newId);
                }
            }
        }
    }
    
    public void wallsAndGatesRegularBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) { // A gate
                    bfs(rooms, i, j);
                }
            }
        }
    }
    
    void bfs(int[][] rooms, int i, int j) {
        int m = rooms.length;
        int n = rooms[0].length;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i * n + j);
        
        while (!queue.isEmpty()) {
            int id = queue.poll();
            int x = id / n;
            int y = id % n;
            
            for (int[] dir : direction) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                int newId = newX * n + newY;
                
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] > rooms[x][y] + 1) { // Changed here
                    rooms[newX][newY] = rooms[x][y] + 1;
                    queue.offer(newId);
                }
            }
        }
    }
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) { // A gate
                    dfs(rooms, i, j);
                }
            }
        }
    }
    
    void dfs(int[][] rooms, int x, int y) {
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int[] dir : direction) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] > rooms[x][y] + 1) { // Changed here
                rooms[newX][newY] = rooms[x][y] + 1;
                dfs(rooms, newX, newY);
            }
        }
    }
}