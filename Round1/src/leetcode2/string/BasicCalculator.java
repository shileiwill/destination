package leetcode2.string;

import java.util.Stack;
/**
 * 224. Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
 */
public class BasicCalculator {
    // Take advantage of Basic Calculator II
    public int calculat2e(String s) {
        while (s.indexOf("(") != -1) {
            int open = s.lastIndexOf("(");
            int close = s.indexOf(")", open);
            
            int res = calculateSubSection(s.substring(open + 1, close).replaceAll("--", "+"));
            s = s.substring(0, open) + res + s.substring(close + 1);
        }
        
        s = s.replaceAll("--", "+");
        return calculateSubSection(s);
    }
    
    public int calculateSubSection(String s) {
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
    
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        int sign = 1;
        int res = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += num * sign;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                res += num * sign;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                
                sign = 1;
                res = 0; // Set result rather than num. num must be 0 here, because the legal char before ( must be + or -
            } else if (c == ')') {
                res += sign * num; // Current Sum Result
                
                res *= stack.pop(); // Previous sign in stack before (
                res += stack.pop(); // Previous Sum Result before (
                
                num = 0;
            }
        }
        
        if (num != 0) { // To deal with single character
            res += sign * num;
        }
        
        return res;
    }
}