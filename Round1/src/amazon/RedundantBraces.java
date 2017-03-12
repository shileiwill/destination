package amazon;

import java.util.Stack;

/**
 * Write a program to validate if the input string has redundant braces?
Return 0/1 
 0 -> NO 1 -> YES 

Input will be always a valid expression

and operators allowed are only + , * , - , /

Example:

((a + b)) has redundant braces so answer will be 1
(a + (a + b)) doesn't have have any redundant braces so answer will be 0
 */
public class RedundantBraces {
	public int braces(String a) {
	    Stack<Character> stack = new Stack<Character>();
	    
	    for (int i = 0; i < a.length(); i++) {
	        char c = a.charAt(i);
	        
	        if (c == ')') {
	            // pop and calculate
	            int len = 0;
	            while (!stack.isEmpty() && stack.peek() != '(') {
	                len++;
	                stack.pop();
	            }
	            
	            if (len <= 1) {
	                return 1;
	            }
	            stack.pop();
	        } else {
	            stack.push(c);
	        }
	    }
	    
	    return 0;
	}
}
