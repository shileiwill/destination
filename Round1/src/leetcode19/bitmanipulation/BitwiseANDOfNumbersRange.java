package leetcode19.bitmanipulation;
/**
 * 201. Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
 */
public class BitwiseANDOfNumbersRange {

    public int rangeBitwiseAnd2(int m, int n) {
        while (n > m) {
            n &= (n - 1); // Flip the last 1 to 0
        }
        
        return n;
    }
    // Find common prefix. Take care of the smallest and largest(has the highest 1) is enough?
    public int rangeBitwiseAnd(int m, int n) {
        int step = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            step++;
        }
        
        return m << step;
    }

}
