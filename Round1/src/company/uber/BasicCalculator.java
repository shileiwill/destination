package company.uber;

import java.util.Stack;

public class BasicCalculator {

	public static void main(String[] args) {
		BasicCalculator cal = new BasicCalculator();
		int res = cal.calculator("3 + 5 * 4");
		System.out.println(res);
	}

	int calculator(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}
			
			if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
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
				
				sign = c;
				num = 0;
			}
		}
		
		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		
		return res;
	}
}
