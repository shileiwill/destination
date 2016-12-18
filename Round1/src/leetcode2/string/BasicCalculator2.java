package leetcode2.string;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 227. Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
 */
public class BasicCalculator2 {
    // Doesn't work
    public int calculate2(String s) {
        LinkedList<Integer> stack1 = new LinkedList<Integer>();
        LinkedList<Character> stack2 = new LinkedList<Character>();
        
        s = s + "*1";
        int left = 0, right = 0;
        for (right = 0; right < s.length(); right++) {
            if (s.charAt(right) == ' ' || Character.isDigit(s.charAt(right))) {
                continue;
            }
            
            if (s.charAt(right) == '+' || s.charAt(right) == '-') {
                stack1.addLast(Integer.parseInt(s.substring(left, right).trim()));
                stack2.addLast(s.charAt(right));
            } else if (s.charAt(right) == '*' || s.charAt(right) == '/') {
                int prev = stack1.removeLast();
                int cur = Integer.parseInt(s.substring(left, right).trim());
                
                int res = 0;
                if (s.charAt(right) == '*') {
                    res = prev * cur;
                } else {
                    res = prev / cur;
                }
                
                stack1.addLast(res);
            }
        }
        
        while (!stack2.isEmpty()) { // 2 stacks should have same size
        
            char op = stack2.pollFirst();
            int first = stack1.pollFirst();
            int second = stack1.pollFirst();
            int res = 0;
            
            switch (op) {
                case '+' :
                    res = first + second;
                    break;
                case '-' :
                    res = first - second;
                    break;
            }
            
            stack1.addFirst(res);
        }
        
        return stack1.isEmpty() ? Integer.parseInt(s.trim()) : stack1.poll();
    }
    
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        // By default, +0
        int num = 0;
        char sign = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            // Leave space for the last digit, need to do calculation
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-num);
                        break;
                    case '*' :
                        stack.push(stack.pop() * num);
                        break;
                    case '/' :
                        stack.push(stack.pop() / num);
                        break;
                }
                
                num = 0;
                sign = c; // If it is the last digit, this sign will be a digit, but it doesn't matter at that time.
            }
        }
        
        int res = 0;
        for (int val : stack) {
            res += val;
        }
        
        return res;
    }
}