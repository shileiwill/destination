package leetcode2.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 186. Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
 */
public class ReverseWordsInAString2 {
    public void reverseWords2(char[] s) {
        Deque<String> stack = new LinkedList<String>();
        
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            if (c == ' ') {
                stack.push(cur.toString());
                cur = new StringBuilder();
            } else {
                cur.append(c);
            }
            
            if (i == s.length - 1) { // Final
                stack.push(cur.toString());
            }
        }
        
        int index = 0;
        while (!stack.isEmpty()) {
            String str = stack.pop();
            for (char c : str.toCharArray()) {
                s[index++] = c;
            }
            if (index < s.length) {
                s[index++] = ' ';
            }
        }
    }
    
    // 3 steps
    public void reverseWords(char[] s) {
        // Reverse the whole sentence
        reverse(s, 0, s.length - 1);
        
        // Reverse each word
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
        
        // Reverse last word
        reverse(s, start, s.length - 1);
    }
    
    void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}