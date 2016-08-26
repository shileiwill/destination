package chapter5.dp;
/**
 * 361. Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell. 
Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */
public class MaxKilledEnemy {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;
        
        int max = 0;
        int rowKilled = 0; // Because it will go row by row, no need to have an array
        int[] colKilled = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Every Column
                if (i == 0 || grid[i - 1][j] == 'W') { // The first row OR the one upstairs is Wall, need to recalculate
                    colKilled[j] = 0;
                    for (int k = i; k < m; k++) {
                        if (grid[k][j] == 'W') {
                            break;
                        } else if (grid[k][j] == 'E') {
                            colKilled[j]++;
                        }
                    }
                }
                // Every Row
                if (j == 0 || grid[i][j - 1] == 'W') { // The left one is Wall, recalculate
                    rowKilled = 0;
                    for (int k = j; k < n; k++) {
                        if (grid[i][k] == 'W') {
                            break;
                        } else if (grid[i][k] == 'E') {
                            rowKilled++;
                        }
                    }
                }
                
                if (grid[i][j] == '0') {
                    max = Math.max(max, rowKilled + colKilled[j]);
                }
            }
        }
        
        return max;
    }
}
