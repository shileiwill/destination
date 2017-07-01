package company.linkedin;
/**
 * Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, 
 * bit 2 and bit 3 are swapped, and so on).

Have you met this question in a real interview? Yes
Example
5 = (101)2 => (1010)2 = 10
 */
public class SwapBits {

	public static void main(String[] args) {
		int res = swapOddEvenBits(-2);
		System.out.println(res);
	}

	/**
	 My answer can't pass test case, -2. It works now, after adding "? 0 : 1;"

Form negative numbers by taking the complement (apply the not operator) of the corresponding positive number and adding one.
Eight bit two's complement binary numbers:

-4ten = not 00000100 + 1two = 11111011 + 1two = 11111100two

-128ten = not 10000000 + 1two = 01111111 + 1two = 10000000two

	 */
    public static int swapOddEvenBits(int x) {
        int N = 1;
        for (int i = 0; i < 32; i += 2) {
            int digitOdd = ((N << i) & x) == 0 ? 0 : 1;
            int digitEven = ((N << (i + 1)) & x) == 0 ? 0 : 1;
            
            if (digitOdd != digitEven) {
                if (digitOdd == 1) {
                    x = x & (~(1 << i));
                    x = x | (1 << (i + 1));
                } else {
                    x = x | (1 << i);
                    x = x & (~(1 << (i + 1)));
                }
            }
        }
        
        return x;
    }
    
/*

    11 10 9 8 |7 6 5 4 | 3 2 1 0
    0  1 0 1 |0 1 0 1 | 0 1 0 1
           5 |      5 |     5 |
    Therefore, to extract bits set at index, 0, 2, 4, 6, 8 .... one need to use the above mask of 0x55555555. Remember each 5 represents 4 bit.


 */
    public void swapNumberGood(int m) {
    	return ((m & 0x55555555) << 1) | ((m & 0xaaaaaaaa) >> 1);
    }
}
