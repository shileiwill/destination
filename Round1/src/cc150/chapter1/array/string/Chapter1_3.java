package cc150.chapter1.array.string;

import java.util.Arrays;

public class Chapter1_3 {

	public static void main(String[] args) {
		String str1 = "abcde";
		String str2 = "fdabe";
		
		boolean res = permutation(str1, str2);
		System.out.println(res);
	}
	
	static boolean permute(String str1, String str2) {
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		
		Arrays.sort(c1);
		Arrays.sort(c2);
		
		String str3 = new String(c1);
		String str4 = new String(c2);
		
		return str3.equals(str4);
	}
	
	// O(n) solution. we can check if 2 strings have identical character counts
	static boolean permutation(String str1, String str2) {
		int[] letters = new int[128]; // ASCII character set OR we can just use HashMap
		
		for (char c : str1.toCharArray()) {
			letters[c]++; // Just use the char, it will start from 0
		}
		
		for (char c : str2.toCharArray()) {
			letters[c]--;
			if (letters[c] < 0) {
				return false;
			}
		}
		
		return true;
	}
}
