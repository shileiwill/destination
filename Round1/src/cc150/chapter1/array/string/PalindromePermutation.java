package cc150.chapter1.array.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PalindromePermutation {

	public static void main(String[] args) {
		PalindromePermutation pp = new PalindromePermutation();
		String str = "Tacocf  ee at";
		boolean res = pp.isPermutationOfPalindrome2(str);
		System.out.println(res);
	}
	
	boolean palindromePermutationSort(String str) {
		str = str.toLowerCase();
		char[] charArr = str.toCharArray();
		
		Arrays.sort(charArr); // Empty space will be at the beginning
		
		int i = 1;
		boolean single = false;
		for (i = 1; i < charArr.length; i = i + 2) {
			char prev = charArr[i - 1];
			char cur = charArr[i];
			System.out.print(prev + "--" + cur);
			
			if (prev == ' ') {
				i = i - 1;
				continue;
			}
			
			if (cur != prev) {
				if (single) {
					return false;
				}
				single = true;
				i = i - 1;
			}
		}
		
		if (i == charArr.length) {
			return !single;
		} else {
			return true;
		}
	}
	
	boolean palindromePermutation(String str) {
		str = str.toLowerCase();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (cur == ' ') { // Ignore space
				continue;
			}
			if (map.containsKey(cur)) {
				map.put(cur, map.get(cur) + 1);
			} else {
				map.put(cur, 1);
			}
		}
		
		boolean single = false;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			char key = entry.getKey();
			int count = entry.getValue();
			
			if (count % 2 != 0) {
				if (single) { // If single already exist, return false
					return false;
				}
				single = true;
			}
		}
		
		return true;
	}
	
	// Another solution using Array and using only 1 iteration
	int getCharNumber(char ch) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int c = Character.getNumericValue(ch);
		
		if (c >= a && c <= z) {
			return c - a;
		}
		
		return -1;
	}
	
	boolean isPermutationOfPalindrome(String str) {
		// Use an array, with size 26
		int[] dict = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		int oddCount = 0; // Count odd numbers
		
		for (char c : str.toCharArray()) {
			int num = getCharNumber(c);
			if (num != -1) {
				dict[num]++;
				if (dict[num] % 2 == 1) {
					oddCount++;
				} else {
					oddCount--;
				}
			}
		}
		
		return oddCount <= 1;
	}
	
	// Bit manipulation
	boolean isPermutationOfPalindrome2(String str) {
		int bitVector = calBitVector(str);
		return bitVector == 0 || checkOnlyOneAppearance(bitVector);
	}
	
	int calBitVector(String str) {
		int bitVector = 0;
		
		for (char c : str.toCharArray()) {
			int num = getCharNumber(c);
			bitVector = toggle(bitVector, num);
		}
		
		return bitVector;
	}
	
	int toggle(int bitVector, int num) {
		if (num == -1) {
			return bitVector; // Return original one
		}
		
		int mask = 1 << num;
		if ((bitVector & mask) == 0) { // Used to be 0
			bitVector = bitVector | mask;
		} else { // Used to be 1, will change to 0
			bitVector &= ~mask;
		}
		
		return bitVector;
	}
	boolean checkOnlyOneAppearance(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}
}
