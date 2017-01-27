package leetcode19.bitmanipulation;
/**
 * 476. Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 */
public class NumberComplement {

    // https://discuss.leetcode.com/topic/74642/java-1-line-bit-manipulation-solution
    public int findComplement2(int num) {
        int highest = Integer.highestOneBit(num);
        int mask = (highest << 1) - 1;
        return (~num) & mask;
    }
    
    // Given a positive integer
    public int findComplement(int num) {
        int highest = 31;
        int mask = 1 << highest;
        
        while ((num & mask) == 0) {
            highest--;
            mask = 1 << highest;
        }
        
        mask = (mask << 1) - 1;
        return (~num) & mask;
    }

}
