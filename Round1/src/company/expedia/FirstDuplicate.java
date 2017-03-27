package company.expedia;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstDuplicate {

	public static void main(String[] args) {
		FirstDuplicate fd = new FirstDuplicate();
		String s = "abccfegbaed";
		char c = fd.firstDuplicate(s);
		System.out.println(c);
	}

	char firstDuplicate(String s) {
		char[] arr = s.toCharArray();
		
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			char key = entry.getKey();
			int count = entry.getValue();
			
			if (count > 1) {
				return key;
			}
		}
		
		return ' ';
	}
}
