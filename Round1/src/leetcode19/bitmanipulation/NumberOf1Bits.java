package leetcode19.bitmanipulation;
/**
 * 191. Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1); // This will flip the last 1 digit to 0
        }
        
        return count;
    }
    
    public static int hammingWeight(long n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        
        return count;
    }

    public static void main(String[] args) {
		long a = 2147483648L;
		int count = hammingWeight(a);
		System.out.println(count);
	}
}
