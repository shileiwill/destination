package leetcode7.binarysearch;
/**
 * 50. Implement pow(x, n).
 */
public class Pow {
    public double myPowNaive(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }
        double res = 1;
        for (int i = 0; i < Math.abs(n); i++) {
            res = res * x;
        }
        return n <= 0 ? 1.0 / res : res;
    }
    
    public double myPowIterative(double x, int n) {
        if (x == 0) {
            return 0;
        }
        
        long m = n;
        if (m == 0) {
            return 1;
        }
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }
        double res = 1;
        while (m > 0) {
            // m is odd, only the very first digit matters
            if ((m & 1) == 1) {
                res *= x;
            }
            x *= x; // Accumulate x
            m >>= 1; // Half m
        }
        
        return res;
    }
    
    public double myPow(double x, int n) {
        long m = n;
        if (m == 0) {
            return 1;
        }
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }
        
        double res = myPow(x, (int)(m / 2));
        return (m & 1) == 1 ? x * res * res : res * res;
    }
}
