package leetcode7.binarysearch;
/**
 * 367. Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int x) {
        if (x == 0 || x == 1) {
            return true;
        }
        
        long left = 1, right = x / 2;
        
        while (true) {
            long mid = left + (right - left) / 2;
            System.out.println(mid);
            
            if (mid * mid == x) {
                return true;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else if ((mid + 1) * (mid + 1) > x) { // mid * mid < x but (mid + 1) * (mid + 1) > x
                return false;
            } else { // mid * mid < x and (mid + 1) * (mid + 1) <= x
                left = mid + 1;
            }
            
        }
    }
}
