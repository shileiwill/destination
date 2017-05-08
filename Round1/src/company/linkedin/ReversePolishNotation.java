package company.linkedin;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ReversePolishNotation {

	public static void main(String[] args) {
		ReversePolishNotation rpn = new ReversePolishNotation();
		String[] tokens = {"3", "!", "4", "+", "5", "-"};
		System.out.println(rpn.evalRPN(tokens));
	}


    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<String>();
        
        Set<String> operators = new HashSet<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        
        for (String s : tokens) {
            if (operators.contains(s)) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                
                String res = helper(num1, num2, s);
                stack.push(res);
            } else if (s.equals("!")) {
            	String num = stack.pop();
            	int res = getPermutation(Integer.valueOf(num));
            	stack.push(res + "");
            } else {
                stack.push(s);
            }
        }
        
        return Integer.valueOf(stack.pop());
    }
    
    int getPermutation(int n) {
    	int res = 1;
    	for (int i = 2; i <= n; i++) {
    		res *= i;
    	}
    	return res;
    }
    
    String helper(String operand1, String operand2, String s) {
        int val1 = Integer.parseInt(operand1);
        int val2 = Integer.parseInt(operand2);
        int res = 0;
        switch (s) {
            case "+":
                res = val1 + val2;    
                break;
            case "-":
                res = val1 - val2;
                break;
            case "*":
                res = val1 * val2;
                break;
            case "/":
                res = val1 / val2;
                break;
        }
        
        return res + "";
    }

}
