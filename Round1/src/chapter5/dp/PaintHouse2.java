package chapter5.dp;
/**
 * 265. There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color. 

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses. 

Note:
 All costs are positive integers.

 */
public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        // 前i个房子，第i个房子刷k的颜色，总共cost
        int[][] hash = new int[costs.length][costs[0].length];
        
        for (int i = 0; i < costs[0].length; i++) {
            hash[0][i] = costs[0][i];
        }
        
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < costs[i].length; j++) {
                int minWithoutJ = Integer.MAX_VALUE;
                for (int k = 0; k < hash[i - 1].length; k++) {
                    if (k == j) {
                        continue;
                    }
                    minWithoutJ = Math.min(minWithoutJ, hash[i - 1][k]);
                }
                hash[i][j] = costs[i][j] + minWithoutJ;
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            res = Math.min(res, hash[costs.length - 1][i]);
        }
        
        return res;
    }
    
    public int minCostIIBetter(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        
        int houseCount = costs.length;
        int colorCount = costs[0].length;
        
        int[] hash = new int[colorCount];
        int min1 = 0, min2 = 0;
        int preMin1 = 0, preMin2 = 0;
        
        for (int i = 0; i < houseCount; i++) {
            preMin1 = min1; // min1, min2 for the previous house
            preMin2 = min2;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            
            for (int j = 0; j < colorCount; j++) {
                if (hash[j] != preMin1) { // the previous min is not paint with this color
                    hash[j] = costs[i][j] + preMin1;
                } else {
                    hash[j] = costs[i][j] + preMin2;
                }
                
                if (hash[j] < min1) { // The is a min1, min2 for every house
                    min2 = min1;
                    min1 = hash[j];
                } else if (hash[j] < min2){
                    min2 = hash[j];
                }
            }
        }
        
        return min1;
    }
}
/**
This is a classic back pack problem. 
 -- Define dp[n][k], where dp[i][j] means for house i with color j the minimum cost. 
 -- Initial value: dp[0][j] = costs[0][j]. For others, dp[i][j] = Integer.MAX_VALUE;, i >= 1
 -- Transit function: dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + cost[i][j]), where k != j.
 -- Final state: Min(dp[n - 1][k]).
 
 How to optimize to O(nk)? http://www.cnblogs.com/airwindow/p/4804011.html
 
 This problem is very elegant if you take the time comlexity constraint into consideration. 
It actually share the same dynamic programming idea as Paint House |.

If we continue follow the old coding structure, we definitely would end up with the time complexity: O(nk^2).
level 1: n is the total number of houses we have to paint. 
level 2: the first k represent for each house we need to try k colors. 
level 3: the second k was caused by the process to search the minimum cost (if not use certain color).

Apparently, if we want reach the time complexity O(nk), we have to optimize our operation at level 3. 
If we choose the color[i][j], how could we reduce the comparision between (color[i-1][0] to color[i-1][k], except color[i-1][j])
And we know there are acutally extra comparisions, since fore each color, we have to find the smallest amongst other colors. 

There must be way to solve it, Right?
Yup!!! There is a magic skill for it!!!
Let us assume, we have "min_1" and "min_2". 
min_1 : the lowest cost at previous stage.
min_2 : the 2nd lowest cost at previous stage. 

And we have the minimum costs for all colors at previous stage.
color[i-1][k]

Then, iff we decide to paint house "i" with color "j", we can compute the minimum cost of other colors at "i-1" stage through following way.
case 1: iff "color[i-1][j] == min_1", it means the min_1 actually records the minimum value of color[i-1][j] (previous color is j), we have to use min_2;
case 2: iff "color[i-1][j] != min_1", it means min_1 is not the value of color[i-1][j] (previous color is not j), we can use the min_1's color.
Note: iff "pre_min_1 == pre_min_2", it means there are two minimum costs, anyway, no matter which color is pre_min_1, we can use pre_min_2.
**/