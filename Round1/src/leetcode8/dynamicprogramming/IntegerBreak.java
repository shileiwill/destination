package leetcode8.dynamicprogramming;
/**
 * 343. Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        int[] hash = new int[n + 1];
        hash[1] = 1; // Why 1?
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // It can be j * 1 at least
                hash[i] = Math.max(hash[i], Math.max(j, hash[j]) * Math.max(i - j, hash[i - j]));
            }
        }
        
        return hash[n];
    }
}
