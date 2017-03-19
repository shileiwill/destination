package leetcode11.stack;

import java.util.Stack;

/**
 * 394. Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
    // If this is not easy to understand, try next version
    public String decodeString2(String s) {
        Stack<String> strStack = new Stack<String>();
        Stack<Integer> intStack = new Stack<Integer>();
        
        int idx = 0;
        String res = "";
        while (idx < s.length()) { // Iterate every character
            char c = s.charAt(idx);
            
            if (Character.isDigit(c)) {
                // StringBuilder sb = new StringBuilder(c + ""); // If not, char will be a number
                // while (Character.isDigit(s.charAt(++idx))) {
                //     sb.append(s.charAt(idx) + "");
                // }
                // intStack.push(Integer.parseInt(sb.toString()));
                int count = (c - '0');
                while (Character.isDigit(s.charAt(++idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                }
                intStack.push(count);
            } else if (c == '[') { // Be cautious here, why start a new string when encounter left square
                // Will start a new string, add the previous one
                strStack.push(res);
                res = "";
                idx++;
            } else if (c == ']') {
                StringBuilder sb = new StringBuilder(strStack.pop());
                int count = intStack.pop();
                
                for (int i = 0; i < count; i++) {
                    sb.append(res);
                }
                
                res = sb.toString(); // The new res
                idx++;
                
            } else {
                res += c;
                idx++;
            }
        }
        
        return res;
    }
    
    public String decodeString(String s) {
        Stack<String> strStack = new Stack<String>();
        Stack<Integer> intStack = new Stack<Integer>();
    
        strStack.push(""); // The very first beginning. This will guarantee stack is never empty
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
                int count = c - '0';
                while (Character.isDigit(s.charAt(++i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                }
                
                i--; // Will have another i++ at the end
                intStack.push(count);
            } else if (c == '[') {
                strStack.push(""); // Push an empty string, so it will be easier to append later
            } else if (c == ']') {
                String item = strStack.pop();
                int count = intStack.pop();
                StringBuilder sb = new StringBuilder();
                
                for (int k = 0; k < count; k++) {
                    sb.append(item);
                }
                
                strStack.push(strStack.pop() + sb.toString()); // Need to pop one (may be ""), then append
            } else {
                strStack.push(strStack.pop() + c);
            }
            
            i++;
        }
        
        return strStack.pop();
    }
}
