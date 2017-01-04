package leetcode7.binarysearch;
/**
 * 69. Implement int sqrt(int x).

Compute and return the square root of x.
 */
public class MySqrt {
    public int mySqrt2(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        return helper2(1, x / 2, x);
    }
    // This will overflow
    int helper2(int left, int right, int x) {
        int mid = left + (right - left) / 2;
        
        int res = mid * mid;
        int resLess = (mid - 1) * (mid - 1);
        int resMore = (mid + 1) * (mid + 1);
        if ((res == x) || (x < resMore && x > resLess)) { // May not need (res == x)
            return mid;
        } else if (res < x) {
            return helper2(mid + 1, right, x);
        } else {
            return helper2(left, mid - 1, x);
        }
    }
    
    public static void main(String[] args) {
    	MySqrt ms = new MySqrt();
    	int mySqrt = ms.mySqrt(4);
    	System.out.println(mySqrt);
	}
    
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        return helper(1, x / 2, x);
    }
    
    int helper(int left, int right, int x) {
        int mid = left + (right - left) / 2;
        
        if (mid > x / mid) {
            return helper(left, mid - 1, x);
        } else {
            if ((mid + 1) > x / (mid + 1)) {
                return mid;
            }
            return helper(mid + 1, right, x);
        }
    }
    
    public int mySqrtIterative(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int left = 1, right = x / 2;
        
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if ((mid + 1) > x / (mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            }
        }
    }
}
