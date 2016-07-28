package chapter5.dp;

import java.util.ArrayList;

/**
 * Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

 Notice

You can assume each number in the array is a positive integer and not greater than 100.

Have you met this question in a real interview? Yes
Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.
 * @author Lei
 *
 */
public class MinimumAdjustmentCost {
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max, A.get(i));
        }
        // hash[i][v] 前i个数，把第i个数调整为v，满足相邻两数<= target， 所需要的最小代价
        int[][] hash = new int[A.size()][max + 1];
        
        // What is the cost to change the first element to i
        for (int i = 1; i <= max; i++) {
            hash[0][i] = Math.abs(A.get(0) - i);
        }
        
        for (int i = 1; i < A.size(); i++) {
            for (int j = 1; j <= max; j++) { // Positive integer
                hash[i][j] = Integer.MAX_VALUE;
                // Why we have this loop
                for (int k = 1; k <= max; k++) { // 代表i前边一个数的浮动. 一环套一环？
                    if (Math.abs(k - j) > target) {
                        continue;
                    }
                    int diff = Math.abs(j - A.get(i)) + hash[i - 1][k];
                    hash[i][j] = Math.min(hash[i][j], diff);
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= max; i++) {
            res = Math.min(res, hash[A.size() - 1][i]);
        }
        
        return res;
    }
}
