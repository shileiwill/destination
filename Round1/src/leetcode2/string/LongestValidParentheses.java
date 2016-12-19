package leetcode2.string;

import java.util.Stack;
/**
 * 32. Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 */
public class LongestValidParentheses {
    public int longestValidParentheses2(String s) {
        // Stack contains the indexes of parentheses
        // LinkedList<Integer> stack = new LinkedList<Integer>();
        // Deque<Integer> stack = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        int res = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                // Can form a valid pair
                stack.pop();
                res = Math.max(res, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        
        return res;
    }
    
    /*
    DP. If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.

Else if s[i] is ')'

     If s[i-1] is '(', longest[i] = longest[i-2] + 2

     Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]

For example, input "()(())", at i = 5, longest array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.
    */
    public int longestValidParentheses(String s) {
        // The max length ending with index i
        int[] hash = new int[s.length()];
        int res = 0;
        // From 1. hash[0] is always 0, no matter it is ( or )
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                hash[i] = 0; // No need to do this, it is 0 by default
            } else { // ")"
                if (s.charAt(i - 1) == '(') {
                    hash[i] = i - 2 >= 0 ? hash[i - 2] + 2 : 2;
                    res = Math.max(res, hash[i]);
                } else {
                    // The previous one is also close ), will combine previous length
                    if (i - hash[i - 1] - 1 >= 0 && s.charAt(i - hash[i - 1] - 1) == '(') {
                        hash[i] = hash[i - 1] + 2 + (i - hash[i - 1] - 2 >= 0 ? hash[i - hash[i - 1] - 2] : 0);
                        res = Math.max(res, hash[i]);
                    }
                }
            }
        }
        
        return res;
    }
}