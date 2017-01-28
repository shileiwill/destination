package leetcode20.math;
/**
 * 258. Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful.
 */
public class AddDigits {

    // https://discuss.leetcode.com/topic/41017/simple-java-solution-no-recursion-loop/3
    public int addDigits2 (int num) {
        
        while (num / 10 != 0) {
            int digit = num % 10;
            num = num / 10;
            
            num += digit;
        }
        
        return num;
    }
    
    public int addDigits (int num) {
        if (num == 0) {
            return num;
        }
        
        if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }

}
