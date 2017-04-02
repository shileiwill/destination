package company.yahoo;

import java.util.HashMap;
import java.util.Map;

public class MaxSubstringWithoutRepeating {

	public static void main(String[] args) {
		MaxSubstringWithoutRepeating ms = new MaxSubstringWithoutRepeating();
		int res = ms.maxString("Helloword");
		System.out.println(res);
	}

	int maxString(String str) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		int left = 0, right = 0;
		int len = str.length();
		
		int count = 0;
		int max = Integer.MIN_VALUE;
		while (right < len) {
			char now = str.charAt(right);
			if (map.containsKey(now)) {
				count++;
				map.put(now, map.get(now) + 1);
			} else {
				map.put(now, 1);
			}
			right++; // right is the first duplicate
			
			while (count != 0) {
				char leftChar = str.charAt(left);
				
				if (map.get(leftChar) > 1) {
					count--;
				}
				map.put(leftChar, map.get(leftChar) - 1);
				left++;
			}
			max = Math.max(max, right - left);
		}
		
		return max;
	}
}
