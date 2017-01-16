package leetcode11.stack;

import java.util.Stack;

/**
 * 456. Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class Find132Pattern {
    /*
    The idea is that we can use a stack to keep track of previous min-max intervals.

Here is the principle to maintain the stack:

For each number num in the array

If stack is empty:

push a new Pair of num into stack
If stack is not empty:

if num < stack.peek().min, push a new Pair of num into stack

if num >= stack.peek().min, we first pop() out the peek element, denoted as last

if num < last.max, we are done, return true;

if num >= last.max, we merge num into last, which means last.max = num.
Once we update last, if stack is empty, we just push back last.
However, the crucial part is:
If stack is not empty, the updated last might:

Entirely covered stack.peek(), i.e. last.min < stack.peek().min (which is always true) && last.max >= stack.peek().max, in which case we keep popping out stack.peek().
Form a 1-3-2 pattern, we are done ,return true
So at any time in the stack, non-overlapping Pairs are formed in descending order by their min value, which means the min value of peek element in the stack is always the min value globally.
    */
    public boolean find132pattern(int[] nums) {
        Stack<Pair> stack = new Stack<Pair>();
        
        for (int num : nums) {
            if (stack.isEmpty() || num < stack.peek().min) {
                stack.push(new Pair(num, num));
            } else if (num > stack.peek().min) {
                Pair last = stack.pop();
                if (last.max > num) {
                    return true;
                } else {
                    last.max = num;
                    
                    while (!stack.isEmpty() && stack.peek().max <= num) { // Both < and <= work
                        stack.pop();
                    }
                    
                    if (!stack.isEmpty() && stack.peek().min < num) {
                        return true;
                    }
                    
                    stack.push(last);
                }
            }
        }
        
        return false;
    }
    
    class Pair {
        int min, max;
        Pair (int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
