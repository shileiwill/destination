package company.linkedin;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthese {
	
	public static void main(String[] args) {
		ValidParenthese vp = new ValidParenthese();
		boolean res = vp.valid2("([({()}){])}");
		System.out.println(res);
	}

	boolean valid(String s) {
		int count = 0;
		
		for (char c : s.toCharArray()) {
			if (c == '(') {
				count++;
			} else if (c == ')') {
				count--;
			}
			
			if (count < 0) {
				return false;
			}
		}
		
		return count == 0;
	}
	
	boolean valid2(String s) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		
		Stack<Character> stack = new Stack<Character>();
		
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					char prev = stack.pop(); // what if stack is empty
					if (map.get(c) != prev) {
						return false;
					}
				}
			}
		}
		
		return stack.isEmpty();
	}
}
