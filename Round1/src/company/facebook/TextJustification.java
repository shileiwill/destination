package company.facebook;

import java.util.*;

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

Facebook version: 不过justify过后的每一行末尾需要插入页码和总页数，页码和总页数也算在每一行的字数里面 e.g. 3/102
 */
public class TextJustification {

	public static void main(String[] args) {

	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<String>();
		
		int start = 0;
		while (start < words.length) {
			int end = start + 1; // Not included
			
			int curLen = words[start].length();
			while (end < words.length && curLen + 1 + words[end].length() <= maxWidth) {
				curLen += words[end].length() + 1;
				end++;
			}
			
			
			StringBuilder sb = new StringBuilder();
			if (end == words.length || end == start + 1) { // Left Justification, last line or only 1 word in line
				for (int i = start; i < end; i++) {
					sb.append(words[i] + " ");
				}
				sb.setLength(sb.length() - 1);
				
				for (int i = sb.length(); i < maxWidth; i++) {
					sb.append(" ");
				}
			} else { // Middle Justify
				int numOfSpaces = end - start - 1;
				int totalSpaces = maxWidth - (curLen - numOfSpaces);
				
				int eachSpace = totalSpaces / numOfSpaces;
				int extraSpace = totalSpaces % numOfSpaces;
				
				for (int i = start; i < end; i++) {
					sb.append(words[i]);
					if (i < end - 1) { // The right most must be to the end
						for (int j = 0; j < eachSpace + (i - start < extraSpace ? 1 : 0); j++) {
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
