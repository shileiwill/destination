package leetcode15.bfs.dfs.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 305. A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIslands2 {
    // https://discuss.leetcode.com/topic/29613/easiest-java-solution-with-explanations/2
    int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        int count = 0;
        int[] islands = new int[m * n];
        Arrays.fill(islands, -1);
        
        for (int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];
            int id = x * n + y;
            islands[id] = id; // assume the new added island is isolated
            count++;
            
            for (int[] dir : direction) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                int newId = newX * n + newY;
                
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && islands[newId] != -1) { // -1 means it is water
                    int p1 = find(islands, id);
                    int p2 = find(islands, newId);
                    
                    if (p1 != p2) { // If the 2 islands are in 2 groups
                        islands[p1] = p2; // union them
                        count--;
                    }
                }
            }
            
            res.add(count);
        }
        
        return res;
    }
    
    // Not efficient enough
    int find2(int[] islands, int id) {
        while (islands[id] != id) {
            id = islands[id];
        }
        
        return islands[id];
    }
    
    int find(int[] islands, int id) {
    	// Try to use Path compression
    	while(id != islands[id]) {
            // islands[id] = islands[islands[id]];   // only one line added. Point to its parent's parent
            // It can also be below, even faster! or add more.
            islands[id] = islands[islands[islands[id]]];
            id = islands[id];
        }
        return id;
    }
}
