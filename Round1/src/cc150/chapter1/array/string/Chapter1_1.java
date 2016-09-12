package cc150.chapter1.array.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter1_1 {

	public static void main(String[] args) {
		Chapter1_1 c1 = new Chapter1_1();
		String str = "abcdHelofg";
		boolean res = c1.uniqueChar(str);
		System.out.println(res);
		boolean res2 = c1.isUniqueChars(str);
		System.out.println(res2);
	}

	boolean uniqueChar(String str) {
		List<Character> exist = new ArrayList<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (exist.contains(cur)) {
				return false;
			} else {
				exist.add(cur);
			}
		}
		
		return true;
	}
	
	boolean uniqueChar2(String str) {
		char[] charArr = str.toCharArray();
		Arrays.sort(charArr);
		
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] == charArr[i - 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	boolean isUniqueChars(String str) {
		int bitVector = 0;
		for (char c : str.toCharArray()) {
			int num = c - 'a';
			int mask = 1 << num;
			
			if ((bitVector & mask) == 0) { // That digit is 0, dont exist, will change to 1
				bitVector |= mask;
			} else { // already exist
				return false;
			}
		}
		
		return true;
	}
}
