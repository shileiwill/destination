package leetcode11.stack;

import java.util.Stack;

/*
 * 316. Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] letters = new int[26];
        boolean[] visited = new boolean[26];
        
        Stack<Character> stack = new Stack<Character>();
        
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            letters[idx]--;
            if (visited[idx]) {
                continue;
            }
            
            // Previous letter in stack is bigger than current, and that letter is still available later. So, pop this one, use lattter one
            while (!stack.isEmpty() && stack.peek() > c && letters[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            
            visited[idx] = true;
            stack.push(c);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()); // Reversed order
        }
        
        return sb.toString();
    }
}
