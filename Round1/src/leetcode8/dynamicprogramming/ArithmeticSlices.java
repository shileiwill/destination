package leetcode8.dynamicprogramming;
/**
 * 413. A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices2(int[] A) {
        int res = 0;
        int[] hash = new int[A.length];
        
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                hash[i] = hash[i - 1] + 1;
            }
            res += hash[i];
        }
        
        return res;
    }
    
    public int numberOfArithmeticSlices(int[] A) {
        int cur = 0, sum = 0;
        
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur += 1;
            } else {
                cur = 0;
            }
            sum += cur;
        }
        
        return sum;
    }
}
