package leetcode20.math;
/**
 * 233. Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
 */
public class NumberOfDigitOne {

    // https://discuss.leetcode.com/topic/27565/java-python-one-pass-solution-easy-to-understand
    
    // Not easy to understand
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int q = n, x = 1, ans = 0;
        while (q > 0) {
            int digit = q % 10;
            q = q / 10;
            
            ans += q * x;
            if (digit == 1) {
                ans += n % x + 1;
            } else if (digit > 1) {
                ans += x;
            }
            
            x = x * 10;
        }
        
        return ans;
    }

}
