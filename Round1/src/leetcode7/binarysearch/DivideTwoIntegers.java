package leetcode7.binarysearch;
/**
 * 29. Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        
        int flag = ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) ? 1 : -1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        
        if (ldivisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (ldividend == 0 || (ldividend < ldivisor)) {
            return 0;
        }
        
        long left = 1, right = ldividend;
        
        while (true) {
            long mid = left + (right - left) / 2;
            if (mid > ldividend / ldivisor) {
                right = mid - 1;
            } else {
                if ((mid + 1) > (ldividend / ldivisor)) {
                    if (flag == 1 && mid > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    } else if (flag == -1 && flag * mid < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    } else {
                        return (int)(flag * mid);
                    }
                }
                left = mid + 1;
            }
        }
    }
}
