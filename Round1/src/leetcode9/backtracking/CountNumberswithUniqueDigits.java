package leetcode9.backtracking;
/**
 * 357. Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

Hint:

A direct way is to use the backtracking approach.
Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
Let f(k) = count of numbers with unique digits with length equals k.
f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 */
public class CountNumberswithUniqueDigits {
    // https://discuss.leetcode.com/topic/48001/backtracking-solution/2
    public int countNumbersWithUniqueDigits2(int n) {
        int count = 1; // A single 0, leading 0
        long max = (long)Math.pow(10, n);
        boolean[] used = new boolean[10]; // Number from 0 to 9
        
        for (int i = 1; i < 10; i++) { // The first digit, leading 0 is not included
            used[i] = true;
            count += helper2(i, max, used);
            used[i] = false;
        }
        
        return count;
    }
    
    // Calculate the count of numbers which are leading with pre and smaller than max
    int helper2(long pre, long max, boolean[] used) {
        int count = 0;
        
        if (pre < max) {
            count++;
        } else {
            return count;
        }
        
        for (int i = 0; i < 10; i++) { // 0 included, all single digit number
            if (!used[i]) {
                used[i] = true;
                long cur = pre * 10 + i;
                count += helper2(cur, max, used);
                used[i] = false;
            }
        }
        
        return count;
    }
    
    // Just replace the boolean array with a single int using bit manipulation
    public int countNumbersWithUniqueDigits1(int n) {
        int count = 1;
        long max = (long)Math.pow(10, n);
        int used = 0;
        
        for (int i = 1; i < 10; i++) {
            used |= (1 << i);
            count += helper(i, max, used);
            used &= ~(1 << i);
        }
        
        return count;
    }
    
    int helper(long pre, long max, int used) {
        int count = 0;
        if (pre < max) {
            count++;
        } else {
            return count;
        }
        
        for (int i = 0; i < 10; i++) {
            if ((used & (1 << i)) == 0) {
                used |= (1 << i);
                long cur = pre * 10 + i;
                count += helper(cur, max, used);
                used &= ~(1 << i);
            }
        }
        
        return count;
    }
    
    // https://discuss.leetcode.com/topic/47983/java-dp-o-1-solution/2
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        
        int res = 10;
        int unique = 9;
        int available = 9;
        
        while (n > 1 && available > 0) {
            unique = unique * available;
            res += unique;
            
            available--;
            n--;
        }
        
        return res;
    }
}
