package leetcode8.dynamicprogramming;
/**
 * 256. There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        
        int prevR = costs[0][0];
        int prevG = costs[0][1];
        int prevB = costs[0][2];
        // We care only about the neighbor houses. So, the 3rd house has nothing to do with the 1st one.
        for (int i = 1; i < costs.length; i++) {
            int curR = Math.min(prevG, prevB) + costs[i][0];
            int curG = Math.min(prevR, prevB) + costs[i][1];
            int curB = Math.min(prevG, prevR) + costs[i][2];
            
            prevR = curR;
            prevG = curG;
            prevB = curB;
        }
        
        return Math.min(prevR, Math.min(prevG, prevB));
    }
}
