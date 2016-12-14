package leetcode2.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();
        
        map2.put(')', '(');
        map2.put('}', '{');
        map2.put(']', '[');
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map2.containsKey(c)) { // Left
                stack.push(c);    
            } else { // Right, need a match
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (stack.pop() != map2.get(c)) {
                        return false;
                    }
                }
            }
        }
        
        // To deal with cases, like [[[[[
        return stack.isEmpty() ? true : false;
    }
}
