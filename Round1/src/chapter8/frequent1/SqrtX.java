package chapter8.frequent1;
/**
 * 69. Implement int sqrt(int x).

Compute and return the square root of x.

 * @author Lei
 *
 */
public class SqrtX {

	public static void main(String[] args) {
		int a = 232323223;
		System.out.println(a * a); // Even though we dont give a type to a*a, it will be int by default. So, no enough
	}

    // Binary search
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        
        // That is why we define long here
        long left = 1, right = x / 2;
        
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            
            if (mid * mid == x) { // Make everything long. Here we need to make sure mid is long as well
                return (int)mid;
            } else if (mid * mid > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (right * right <= x) {
            return (int)right;
        }
        
        return (int)left;
    }
}
