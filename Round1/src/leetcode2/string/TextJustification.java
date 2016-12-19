package leetcode2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        
        int start = 0;
        while (start < words.length) {
            int end = start + 1; // End is not included
            int count = words[start].length();
            while (end < words.length) {
                if (count + words[end].length() + 1 > maxWidth) {
                    break;
                }
                count += words[end].length() + 1; // Increase number of characters, including space
                end++; // Find the first unsatisfied ending index
            }
            
            int numberOfSpaces = end - start - 1;
            
            StringBuilder sb = new StringBuilder();
            if (numberOfSpaces == 0 || end == words.length) {
                // If only 1 word or the last line, left justify (moving to left)
                for (int i = start; i < end; i++) {
                    sb.append(words[i] + " ");
                }
                // Remove last white space
                sb.deleteCharAt(sb.length() - 1);
                // Append extra spaces
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");    
                }
            } else {
                // Middle justify
                int eachSpace = (maxWidth - count) / numberOfSpaces;
                int extraSpace = (maxWidth - count) % numberOfSpaces;
                
                for (int i = start; i < end; i++) {
                    sb.append(words[i]);
                    if (i < end - 1) { // Not the last element
                        for (int j = 0; j <= eachSpace + (i - start < extraSpace ? 1 : 0) ; j++) { // <= here
                            sb.append(" ");
                        }
                    }
                }
            }
            
            res.add(sb.toString());
            start = end;
        }
        
        return res;
    }
}