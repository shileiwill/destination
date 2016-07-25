package chapter5.dp;

import java.util.List;
/**
 * 120. Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * @author Lei
 *
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] hash = new int[triangle.size()][triangle.size()];
        for (int i = 0; i < hash.length; i++) {
            for (int j = 0; j < hash[i].length; j++) {
                hash[i][j] = Integer.MIN_VALUE;
            }
        }
        return helper(triangle, 0, 0, hash);
    }
    
    int helper(List<List<Integer>> triangle, int x, int y, int[][] hash) {
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }
        
        if (hash[x][y] != Integer.MIN_VALUE) {
            return hash[x][y];
        }
        // Take care only of current level, which is x, y.
        hash[x][y] = Math.min(helper(triangle, x + 1, y, hash), helper(triangle, x + 1, y + 1, hash)) + triangle.get(x).get(y);
        
        return hash[x][y];
    }
}
