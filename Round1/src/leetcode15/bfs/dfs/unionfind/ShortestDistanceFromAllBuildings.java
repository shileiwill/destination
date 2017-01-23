package leetcode15.bfs.dfs.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 317. You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistanceFromAllBuildings {
    // https://discuss.leetcode.com/topic/31925/java-solution-with-explanation-and-time-complexity-analysis
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int buildingCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                    Queue<int[]> queue = new LinkedList<int[]>();
                    boolean[][] visited = new boolean[m][n];
                    
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    int level = 1;
                    while (!queue.isEmpty()) {
                        int size = queue.size(); // Level order traversal
                        
                        for (int k = 0; k < size; k++) {
                            int[] cur = queue.poll();
                            int x = cur[0];
                            int y = cur[1];
                            
                            for (int[] dir : direction) {
                                int newX = x + dir[0];
                                int newY = y + dir[1];
                                
                                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] == 0) {
                                    visited[newX][newY] = true;
                                    distance[newX][newY] += level;
                                    reach[newX][newY]++;
                                    queue.offer(new int[]{newX, newY});
                                }
                            }
                        }
                        
                        level++;
                    }
                    
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingCount) {
                    res = Math.min(res, distance[i][j]);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}