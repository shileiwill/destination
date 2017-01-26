package leetcode19.bitmanipulation;
/**
 * 371. Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.
 */
public class SumOfTwoIntegers {

    // https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
    public int getSum2(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return getSum((a ^ b), (a & b) << 1);  
    }
    
    //https://discuss.leetcode.com/topic/49771/java-simple-easy-understand-solution-with-explanation
    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        
        while (b != 0) {
            int carry = a & b; // 都是1不就得carry嘛
            a = a ^ b; // 找出不一样的位来，find the different bit
            b = carry << 1;
        }
        
        return a;
    }

}
