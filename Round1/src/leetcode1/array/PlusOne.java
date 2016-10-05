package leetcode1.array;
/**
 * 66. Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int addition = 1;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            int curSum = digits[i] + addition;
            int curDigit = curSum % 10;
            addition = curSum / 10;
            
            digits[i] = curDigit;
        }
        
        if (addition != 0) {
            int[] res = new int[digits.length + 1];
            for (int i = res.length - 1; i > 0; i--) {
                res[i] = digits[i - 1];
            }
            res[0] = 1;
            return res;
        }
        
        return digits;
    }
}
