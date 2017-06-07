package company.amazon;

import java.util.*;
import java.util.List;
// 394
/**
 * Not the same purpose
 * Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
	
	// It could be nested, Use 2 stacks, A magic is empty string at the beginning
	public static String decodeString394(String s) {
		Stack<String> stack1 = new Stack<String>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		int pos = 0;
		stack1.push("");

		while (pos < s.length()) {
			char now = s.charAt(pos);
			
			if (Character.isDigit(now)) {
				int num = 0;
				while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
					num = num * 10 + (s.charAt(pos) - '0');
					pos++;
				}
				pos--;
				stack2.push(num);
			} else if (now == '[') {
				stack1.push("");
			} else if (now == ']') {
				int count = stack2.pop();
				String pattern = stack1.pop();
				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < count; i++) {
					sb.append(pattern);
				}
				
				stack1.push(stack1.pop() + sb.toString());
			} else {
				stack1.push(stack1.pop() + now);
			}
			
			pos++;
		}
		
		return stack1.pop();
	}

	static Map<Integer, String> map = new HashMap<>();
	public static void main(String[] args) {
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "f");
		map.put(7, "g");
		map.put(8, "h");
		map.put(9, "i");
		map.put(10, "j");
		map.put(11, "k");
		map.put(12, "l");
		map.put(13, "m");
		map.put(14, "n");
		
		String s = "1312";
		List<String> res = decodingMyStyle(s);
		for (String st : res) {
			System.out.println(st);
		}
//		String res = decodeString394("3[a4[d]]2[bc]");
//		System.out.println(res);
	}

	static List<String> decodingMyStyle(String s) {
		List<String> res = new ArrayList<String>();
		helperMyStyle(res, "", s, 0);
		return res;
	}
	
	static void helperMyStyle(List<String> res, String now, String s, int pos) {
		if (pos == s.length()) {
			res.add(now);
			return;
		}
		
		for (int len = 1; len <= 2 && pos + len <= s.length(); len++) {
			String sub = s.substring(pos, pos + len);
			if (isValid(sub)) {
				helperMyStyle(res, now + map.get(Integer.valueOf(sub)), s, pos + len);
			}
		}
	}
	
	static boolean isValid(String s) {
		if (s.charAt(0) == '0') {
			return false;
		}
		
		int val = Integer.valueOf(s);
		return val >= 1 && val <= 14;
	}
	
	
	static List<String> decoding(String s) {
		List<String> res = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		helper(res, sb, s, 0);
		return res;
	}
	
	static void helper(List<String> res, StringBuilder sb, String s, int pos) {
		if (pos >= s.length()) {
			
			res.add(sb.toString());
			return;
		}
		
		if (pos + 1 <= s.length()) {
			int oneDigit = Integer.parseInt(s.substring(pos, pos + 1));
			if (oneDigit >= 1 && oneDigit <= 9) {
				sb.append(map.get(oneDigit));
				helper(res, sb, s, pos + 1);
				sb.setLength(sb.length() - 1);
			}
		}
		
		if (pos + 2 <= s.length()) {
			int twoDigit = Integer.parseInt(s.substring(pos, pos + 2));
			if (twoDigit >= 10 && twoDigit <= 26) {
				sb.append(map.get(twoDigit));
				helper(res, sb, s, pos + 2);
				sb.setLength(sb.length() - 1);
			}
		}
	}
}
