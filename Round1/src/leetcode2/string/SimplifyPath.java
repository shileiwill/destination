package leetcode2.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 71. Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {
    // Use LinkedList as Stack and StringBuilder    
    public String simplifyPath1(String path) {
        String[] arr = path.split("/");
        StringBuilder sb = new StringBuilder("/"); // Add leading slash
        LinkedList<String> stack = new LinkedList<String>();
        
        for (String str : arr) {
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else if (!str.equals(".") && !str.equals("")) {
                stack.add(str);
            }
        }
        
        for (String str : stack) { // Here is the benefits of using LinkedList implementation, from start to end
            sb.append(str + "/");
        }
        
        if (!stack.isEmpty()) { // Since it is LinkedList essentially, stack is still in good shape
            // If it is not empty string in total, remove the last slash
            sb.setLength(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<String>();
        
        for (String str : arr) {
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!str.equals(".") && !str.equals("")) {
                stack.push(str);
            }
        }
        
        // How to add string reversely from stack
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        
        return res.isEmpty() ? "/" : res;
    }
}