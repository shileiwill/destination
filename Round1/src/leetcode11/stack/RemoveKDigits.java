package leetcode11.stack;

import java.util.Stack;

/**
 * 402. Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        // Stack contains the final result, it saves only small digits
        Stack<Character> stack = new Stack<Character>();
        
        if (k >= num.length()) {
            return "0";
        }
        
        // Final stack will be in "ascending or equal" order, like 12345, or 1111, or 12223344
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) { // pop big digits
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        // Initial string may be 12345, or 11111. In these cases, k will not decrease
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb = sb.reverse();
        
        // Remove leading zeros. Leading 0s are very welcome as they make the number small
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
}
