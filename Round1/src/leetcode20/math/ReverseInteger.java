package leetcode20.math;
/**
 * 7. Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    public int reverse(int x) {
        long res = reverse((long)x);
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int)res;
        }
    }
    
    long reverse(long x) {
        boolean isPositive = true;
        if (x > 0) {
            isPositive = true;
        } else if (x < 0) {
            isPositive = false;
            x = -x;        
        } else {
            return 0;
        }
        
        long res = 0;
        while (x != 0) {
            long digit = x % 10;
            x = x / 10;
            res = res * 10 + digit;
        }
        
        return isPositive ? res : -res;
    }

}
