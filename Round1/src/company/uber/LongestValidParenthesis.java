package company.uber;

import java.util.Stack;

public class LongestValidParenthesis {

	public static void main(String[] args) {
		String s = "((())";
		int res = longestValidParentheses(s);
		System.out.println(res);
	}

	public static int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>(); // Stack contains indexes
		stack.push(-1);
		int res = 0;
		
		for (int i = 0; i < s.length(); i++) {
			char now = s.charAt(i);
			
			if (now == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
				stack.pop(); // Throw the left (
				res = Math.max(res, i - stack.peek());
			} else { // Could be ( and )
				stack.push(i);
			}
		}
		
		return res;
	}
	
	public int longestValidParenthesesDP(String s) {
		int[] hash = new int[s.length()];
		hash[0] = 0;
		int res = 0;
		
		for (int i = 1; i < s.length(); i++) {
			char now = s.charAt(i);
			
			if (now == '(') {
				hash[i] = 0;
			} else { // ')'
				if (s.charAt(i - 1) == '(') {
					hash[i] = (i - 2 >= 0 ? hash[i - 2] : 0) + 2;
					res = Math.max(res, hash[i]);
				} else { // Previous is also )
					if (i - hash[i - 1] - 1 >= 0 && s.charAt(i - hash[i - 1] - 1) == '(') {
						// Can form
						hash[i] = hash[i - 1] + 2 + (i - hash[i - 1] - 2 >= 0 ? hash[i - hash[i - 1] - 2] : 0);
						res = Math.max(res, hash[i]);
					}
				}
			}
		}
		
		return hash[s.length() - 1];
	}
}
