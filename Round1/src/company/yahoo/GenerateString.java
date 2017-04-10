package company.yahoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GenerateString {

	public static void main(String[] args) {
		GenerateString gen = new GenerateString();
		String str = gen.generateString(12326);
		System.out.println(str);
		
		List<String> uniqueString = gen.findUniqueString(str);
		for (String s : uniqueString) {
			System.out.print(s + " : ");
		}
		System.out.println();
		
		String str2 = " str ing  hello";
		String[] arr = str2.split(" ");
		System.out.println(arr.length);
		for (String s : arr) {
			if (s.length() == 0) {
				System.out.print("*");
			} else {
				System.out.print(s + "-");
			}
		}
		
		System.out.println("=========================");
		List<String> list = gen.maxContains(str);
		for (String s : list) {
			System.out.print(s + "--");
		}
		
		System.out.println("=========================");
		String str3 = "a3b11c2";
		String str4 = gen.transform(str3);
		System.out.println(str4);
	}
	
	// a2b3c10 -> aabbbccccccc..
	String transform(String str) {
		StringBuilder sb = new StringBuilder();
		int len = str.length();
		
		int left = 0, right = 0;
		while (left < len && right < len) {
			char c = str.charAt(left);
			
			right = left + 1;
			while (right < len && Character.isDigit(str.charAt(right))) {
				right++;
			}
			int count = Integer.parseInt(str.substring(left + 1, right));
			
			for (int i = 0; i < count; i++) {
				sb.append(c);
			}
			
			left = right;
		}
		
		return sb.toString();
	}
	
	List<String> maxContains(String str) {
		str = str.trim();
		String[] arr = str.split("\\s+");
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		int max = 0;
		
		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			String s1 = arr[0];
			for (int j = i + 1; j < arr.length; j++) {
				String s2 = arr[j];
				
				if (s1.length() < s2.length()) {
					continue;
				}
				
				if (s1.contains(s2)) {
					count++;
				}
			}
			
			if (count > max) {
				max = count;
				map.put(max, new ArrayList<String>());
				map.get(max).add(s1);
			} else if (count == max) {
				map.get(max).add(s1);
			}
		}
		
		System.out.println(max);
		return map.get(max);
	}

	String generateString(int len) {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		
		for (int i = 0; i < len; i++) {
			int index = ran.nextInt(27);
			
			if (index == 26) {
				sb.append(" ");
			} else {
				sb.append((char)(index + 'a'));
			}
		}
		
		return sb.toString();
	}
	
	List<String> findUniqueString(String str) {
		String[] arr = str.split(" ");
		List<String> res = new ArrayList<String>();
		
		for (String s : arr) {
			if (s.length() == 0) {
				continue;
			}
			
			if (isUnique(s)) {
				res.add(s);
			}
		}
		
		return res;
	}
	
	boolean isUnique(String s) {
		Set<Character> set = new HashSet<Character>();
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				return false;
			}
			set.add(c);
		}
		
		return true;
	}
}
