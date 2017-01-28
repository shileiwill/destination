package leetcode20.math;
/**
 * 326. Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
 */
public class PowerOfThree {

    public boolean isPowerOfThree2(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 0) {
            if (n == 1) {
                return true;
            }
            
            if (n % 3 != 0) {
                return false;
            } else {
                n = n / 3;
            }
        }
        
        return true;
    }
    
    public boolean isPowerOfThree1(int n) {
        return (n > 0 && (Math.pow(3, 19) % n == 0)); // 3^20 will be out of the scope of Integer
    }
    
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        
        // Logarithm base change rule: logb(x) = logc(x) / logc(b)
        return n == Math.pow(3, Math.round(Math.log(n) / Math.log(3)));// By default, log is e based, can also use log10
    }

}
