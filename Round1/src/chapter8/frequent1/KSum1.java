package chapter8.frequent1;
/**
 * Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
 * @author Lei
 *
 */
public class KSum1 {
    public int kSum(int A[], int k, int target) {
        int[][][] hash = new int[A.length + 1][k + 1][target + 1];
        
        // How to initialize
        for (int i = 0; i <= A.length; i++) {
            hash[i][0][0] = 1; // 从前i个数中，取0个数，组成target为0的方案数量
        }
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    hash[i][j][t] = hash[i - 1][j][t];
                    if (t - A[i - 1] >= 0) {
                        hash[i][j][t] += hash[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        
        return hash[A.length][k][target];
    }
}
