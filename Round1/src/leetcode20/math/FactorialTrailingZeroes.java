package leetcode20.math;
/**
 * 172. Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
 */
public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        if (n == 0) {
            return 0;
        }        
        // 25 provides 2 fives, 125 provides 3
        return n / 5 + trailingZeroes(n / 5);
    }

}
