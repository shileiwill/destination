package leetcode19.bitmanipulation;
/**
 * 397. Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {
        // 直接单独拿出一个函数来
        return (int)longReplacement((long)n);        
    }
    
    long longReplacement(long l) {
        if (l == 1) {
            return 0;
        }
        
        if (l % 2 == 0) {
            return 1 + longReplacement(l / 2);
        } else {
            return 1 + Math.min(longReplacement(l + 1), longReplacement(l - 1));
        }
    }

}
