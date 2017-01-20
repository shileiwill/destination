package leetcode8.dynamicprogramming;
/**
 * 265. There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
 */
public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        
        int hc = costs.length;
        int cc = costs[0].length;
        
        // The sum cost if this house is painted with this color
        int[][] hash = new int[hc][cc];
        int min1 = -1, min2 = -1;
        
        for (int h = 0; h < hc; h++) {
            int last1 = min1, last2 = min2;
            min1 = -1;
            min2 = -1; // The indices of previous 2 minumums
            // Iterate all colors
            for (int c = 0; c < cc; c++) {
                if (c != last1) {
                    hash[h][c] = costs[h][c] + (last1 < 0 ? 0 : hash[h - 1][last1]); 
                } else {
                    hash[h][c] = costs[h][c] + (last2 < 0 ? 0 : hash[h - 1][last2]);
                }
                
                if (min1 < 0 || hash[h][c] < hash[h][min1]) { // Use hash value, dont use the single house value, costs
                    min2 = min1;
                    min1 = c;
                } else if (min2 < 0 || hash[h][c] < hash[h][min2]) {
                    min2 = c;
                }
            }
        }
        
        return hash[hc - 1][min1];
    }
}
