package leetcode11.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 150. Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        
        List<String> operators = new ArrayList<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        
        for (String token : tokens) {
            if (operators.contains(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                
                int res = calculate(operand1, operand2, token);
                stack.push(res);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        
        return stack.pop();
    }
    
    int calculate(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
        }
        
        return -1;
    }
}
