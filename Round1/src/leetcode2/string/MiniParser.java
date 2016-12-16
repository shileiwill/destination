package leetcode2.string;

import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class MiniParser {
    // Doesn't work
    public NestedInteger deserialize2(String s) {
        Stack<NestedInteger> stack = new Stack<NestedInteger>();
        StringBuilder sb = new StringBuilder();
        NestedInteger parent = null;
        
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        
        if (s.equals("[]")) {
            return new NestedInteger();
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '[') {
                stack.push(new NestedInteger());
                sb = new StringBuilder();
            } else if ((c >= '0' && c <= '9') || c == '-') {
                sb.append(c);
            } else if (c == ',') { // Single Integer
                NestedInteger child = null;
                if (sb.length() == 0) {
                    child = new NestedInteger();
                } else {
                    child = new NestedInteger(Integer.parseInt(sb.toString()));
                }
                
                parent = stack.peek();
                parent.add(child);
                
                sb = new StringBuilder();
            } else if (c == ']') { // List Integer
            System.out.println(sb);
                NestedInteger child = null;
                if (sb.length() == 0) {
                    child = new NestedInteger();
                    parent = stack.pop();
                    parent.add(child);
                } else {
                    child = new NestedInteger(Integer.parseInt(sb.toString()));
                    parent = stack.pop();
                    parent.add(child);
                }

                sb = new StringBuilder();
            }
        }
        
        return parent;
    }
    
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<NestedInteger>();
        NestedInteger cur = null;
        
        if (s.isEmpty()) {
            return cur;
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        
        int left = 0, right = 0;
        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (c == '[') {
                if (cur != null) { // [[
                    stack.push(cur);
                }
                cur = new NestedInteger();
                left = right + 1;
            } else if (c == ',') {
                if (s.charAt(right - 1) != ']') { // Number, except the ],
                    int num = Integer.parseInt(s.substring(left, right));
                    cur.add(new NestedInteger(num));
                }
                left = right + 1;
            } else if (c == ']') {
                String num = s.substring(left, right);
                if (!num.isEmpty()) {
                    cur.add(new NestedInteger(Integer.parseInt(num)));
                }
                if (!stack.isEmpty()) {
                    NestedInteger parent = stack.pop();
                    parent.add(cur);
                    cur = parent;
                }
                left = right + 1;
            }
            
        }
        
        return cur;
    }
}