package company.uber;

import java.util.Stack;

public class BasicCalculator {

	public static void main(String[] args) {
		BasicCalculator cal = new BasicCalculator();
		int res = cal.calculatorMyStyle("3+5*4");
		System.out.println(res);
	}

	// Check my own version below
	int calculatorMyStyle2(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0; // To be prepared
		char sign = '+'; // This sign is the sign before num.
		
		int pos = 0;
		while (pos < s.length()) {
			// Skip space here using while loop
			char now = s.charAt(pos);
			
			if (Character.isDigit(now)) {
				num = num * 10 + (now - '0');
				pos++;
				
				if (pos != s.length()) { // Pay attention to the last one
					continue;
				}
			}
			
			switch (sign) {
				case '+':
					stack.push(num);
					break;
				case '-':
					stack.push(-num);
					break;
				case '*':
					stack.push(stack.pop() * num);
					break;
				case '/':
					stack.push(stack.pop() / num);
					break;
			}
			
			// Skip space here again
			sign = now;
			num = 0;
			pos++;
		}
		
		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		
		return res;
	}
	
	// Better. but this doesn't handle empty space
	int calculatorMyStyle(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0; // To be prepared
		char sign = '+'; // This sign is the sign before num.
		
		int pos = 0;
		while (pos < s.length()) {
			// Skip space here using while loop
			
			while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
				num = num * 10 + (s.charAt(pos) - '0');
				pos++;
			}
			
			switch (sign) {
				case '+':
					stack.push(num);
					break;
				case '-':
					stack.push(-num);
					break;
				case '*':
					stack.push(stack.pop() * num);
					break;
				case '/':
					stack.push(stack.pop() / num);
					break;
			}
			
			// Skip space here again
			if (pos < s.length()) {
				sign = s.charAt(pos);
				num = 0;
			}
			
			pos++;
		}
		
		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		
		return res;
	}
}
