package leetcode8.dynamicprogramming;
/**
 * 338. Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Hint:

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = count(i);
        }
        return res;
    }
    
    int count(int num) {
        int count = 0;
        
        while (num != 0) {
            if (num % 2 == 1) {
                count++;
            }
            num = num / 2;
        }
        
        return count;
    }
    
    public int[] countBits2(int num) {
        int[] hash = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            hash[i] = hash[i >> 1] + (i & 1);
        }
        return hash;
    }
    
    // https://discuss.leetcode.com/topic/40195/how-we-handle-this-question-on-interview-thinking-process-dp-solution/2
    public int[] countBits3(int num) {
        int[] hash = new int[num + 1];
        int offset = 1;
        
        for (int i = 1; i <= num; i++) {
            if (offset * 2 == i) {
                offset *= 2;
            }
            
            hash[i] = hash[i - offset] + 1;
        }
        
        return hash;
    }
}
