package company.facebook.others;

import java.util.*;

/**
 * Created by weixwu on 7/11/2017.
 */
public class RemoveInvalidParentheses {

	public String removeInvalid(String s) {
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder(s);

		// 这个for循环只能查出多余的)
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);

			if (cur == ')') {
				if (stack.isEmpty()) { // 这个)是多余的
					sb.replace(i, i + 1, "*");
				} else { // 扔掉一个(
					stack.pop();
				}
			} else if (cur == '(') {
				stack.push(i); // stack中存的都是(的index
			}
		}
		
		// 这个while循环找出多余的(
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			sb.replace(cur, cur + 1, "*");
		}
		
		return sb.toString().replaceAll("\\*", "");
	}

	public static void main(String args[]) {
		RemoveInvalidParentheses rp = new RemoveInvalidParentheses();
		System.out.print(rp.removeInvalid("(a)())((()"));
	}
}
