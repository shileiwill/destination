package company.yahoo;
/**
 * 479. Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].
 */
public class LargestPalindromeProduct {

	public static void main(String[] args) {
		LargestPalindromeProduct lpp = new LargestPalindromeProduct();
		
		int res = lpp.reverse(100);
		System.out.println(res);
	}

    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        
        int mod = (int)Math.pow(10, n);
        int max = (int)Math.pow(10, n) - 1;
        long product = (long)max * (long)max;
        
        int right = (int)(product % mod);
        int left = (int)(product / mod);
        
        if (left == reverse(right)) {
            return (int)(product % 1337);
        }
        
        if (left > right) {
            left--;
        }
        
        product = (long)left * (long)mod + (long)reverse(left);
        while (left != mod / 10) {
            for (int i = max; i > product / i; i--) { // Why i * i > product?
                if (product % i == 0) { // product is divisible by i
                    return (int)(product % 1337);
                }
            }
            
            left--;
            product = (long)left * (long)mod + (long)reverse(left);
        }
        
        return (int)(product % 1337);
    }
    
    int reverse(int val) {
        int res = 0;
        int radix = 10;
        
        while(val != 0) {
            int lastDigit = val % radix;
            val = val / radix;
            res = res * 10 + lastDigit;
        }
        
        return res;
    }
}
