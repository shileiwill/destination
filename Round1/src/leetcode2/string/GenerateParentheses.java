package leetcode2.string;

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
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(res, "", 0, 0, n);
        return res;
    }
    
    // n is pair
    void helper(List<String> res, String str, int open, int close, int n) {
        if (str.length() == n * 2) {
            res.add(str);
            return;
        }
        
        // As long as left is not enough, we can safely add one more left
        if (open < n) { 
            helper(res, str + "(", open + 1, close, n);
        }
        
        // As long as right is fewer than left, safely add one more right
        if (close < open) {
            helper(res, str + ")", open, close + 1, n);
        }
    }
}
