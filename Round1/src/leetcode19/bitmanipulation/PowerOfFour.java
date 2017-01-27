package leetcode19.bitmanipulation;
/**
 * 342. Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {

    // Power of 4 == 4的X次方
    public boolean isPowerOfFour2(int num) {
        // 保证只有一个1 并且 排除1在奇数位的可能(power of only 2), 0101 0101 0101 0101 0101 0101 0101 0101
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) != 0);
    }
    
    public boolean isPowerOfFour(int num) {
        while (num != 0) {
            if (num == 1) {
                return true;
            }
            
            if (num % 4 != 0) {
                return false;
            } else {
                num = num / 4; // Keep dividing 4
            }
        }
        return false;
    }

}
