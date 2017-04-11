package company.yahoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LettersToRoman {

	public static void main(String[] args) {
		LettersToRoman ltr = new LettersToRoman();
//		String s = ltr.intToRoman(5);
		String s = "bcd";
		
		System.out.println(ltr.intToRoman(6));
		ltr.buildMap();
		List<String> res = ltr.romanToPossibleStrings("VI");
		System.out.println("----------------------------");
		for (String s1 : res) {
			System.out.println(s1);
		}
	}

	String letterToRoman(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (char c : s.toCharArray()) {
			String roman = intToRoman(c - 'a' + 1);
			sb.append(roman);
		}
		
		return sb.toString();
	}
	
	// Since English letters are limited to 26, we can use HashMap
	String intToRoman(int num) {
		int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			int times = num / nums[i];
			while (times != 0) {
				sb.append(symbols[i]);
				times--;
			}
			num = num % nums[i];
		}
		
		return sb.toString();
	}
	
	Map<String, Integer> map1 = new HashMap<String, Integer>();
	Map<Integer, String> map2 = new HashMap<Integer, String>();
	
	void buildMap() {
		for (char c = 'a'; c <= 'z'; c++) {
			int num = c - 'a' + 1;
			String roman = intToRoman(num);
			
			map1.put(roman, num);
			map2.put(num, roman);
		}
		
		for (Map.Entry<String, Integer> entry : map1.entrySet()) {
			System.out.print(entry.getKey() + " : " + entry.getValue() + " == ");
		}
		System.out.println();
		for (Map.Entry<Integer, String> entry : map2.entrySet()) {
			System.out.print(entry.getKey() + " : " + entry.getValue() + " == ");
		}
	}
	
	List<String> romanToPossibleStrings(String roman) {
		List<String> res = new ArrayList<String>();
		helper(res, roman, 0, "");
		return res;
	}
	
	void helper(List<String> res, String roman, int pos, String s) {
		if (pos == roman.length()) {
			res.add(s);
			return;
		}
		
		if (pos > roman.length()) {
			return;
		}
		
		for (int i = pos; i < roman.length(); i++) {
			for (int len = 1; len <= 5; len++) {
				if (i + len > roman.length()) {
					break;
				}
				
				String sub = roman.substring(i, i + len);
				if (map1.containsKey(sub)) {
					char ch = (char)(map1.get(sub) - 1 + 'a');
					helper(res, roman, i + len, s + "-" + ch);
				}
			}
		}
	}
}
