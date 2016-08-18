package chapter5.dp;
/**
 * 256. There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color. 

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses. 

Note:
 All costs are positive integers.

 */
public class PaintHouse1 {

	public static void main(String[] args) {

	}
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        ResultType[] hash = new ResultType[costs.length];
        
        hash[0] = new ResultType(costs[0][0], costs[0][1], costs[0][2]);
        
        for (int i = 1; i < costs.length; i++) {
            int red = costs[i][0] + Math.min(hash[i - 1].blue, hash[i - 1].green);
            int blue = costs[i][1] + Math.min(hash[i - 1].red, hash[i - 1].green);
            int green = costs[i][2] + Math.min(hash[i - 1].blue, hash[i - 1].red);
            hash[i] = new ResultType(red, blue, green);
        }
        
        return hash[hash.length - 1].getMin();
    }
    
    class ResultType {
        int red;
        int blue;
        int green;
        
        ResultType(int red, int blue, int green) {
            this.red = red;
            this.blue = blue;
            this.green = green;
        }
        
        int getMin() {
            return Math.min(red, Math.min(blue, green));
        }
    }
}

