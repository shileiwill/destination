package company.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class GenerateParenthese {

	public static void main(String[] args) {
		GenerateParenthese gp = new GenerateParenthese();
		List<String> res = gp.generateParenthese(3);
		for (String s : res) {
			System.out.println(s);
		}
	}

	List<String> generateParenthese(int N) {
		List<String> res = new ArrayList<String>();
		helper(res, "", N, N, N);
		return res;
	}

	private void helper(List<String> res, String now, int N, int left, int right) {
		if (now.length() == N * 2) {
			res.add(now);
			return;
		}
		
		if (left > 0) {
			helper(res, now + "(", N, left - 1, right);
		}
		
		if (right > left) { // 必须得保证前边的（多
			helper(res, now + ")", N, left, right - 1);
		}
	}
}
