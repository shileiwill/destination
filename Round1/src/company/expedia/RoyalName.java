package company.expedia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RoyalName {

	public static void main(String[] args) {
		String[] names = {"PILLIPS II", "PILLIPS V", "PILLIPSSI II", "JACOB IV", "ANDERSON I", "JACOB M"};
		
		RoyalName rn = new RoyalName();
		
		//System.out.println(rn.romanToInt("VII"));
		String[] res = rn.sort(names);
		for (String s : res) {
			System.out.println(s);
		}
	}
	
	String[] sort(String[] names) {
		Comparator<String> com = new Comparator<String>() {
			public int compare(String s1, String s2) {
				String[] arr1 = s1.split(" ");
				String[] arr2 = s2.split(" ");
				
				if (arr1[0].compareTo(arr2[0]) != 0) {
					return arr1[0].compareTo(arr2[0]);
				}
				
				int val1 = romanToInt(arr1[1]);
				int val2 = romanToInt(arr2[1]);
				
				return val2 - val1;
			}
		};
		
		Arrays.sort(names, com);
		
		return names;
	}
	
	int romanToInt(String roman) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		char[] arr = roman.toCharArray();
		int res = map.get(arr[arr.length - 1]);
		int prev = map.get(arr[arr.length - 1]);
		
		for (int i = arr.length - 2; i >= 0; i--) {
			int now = map.get(arr[i]);
			
			if (now < prev) {
				res -= now;
			} else {
				res += now;
			}
		}
		
		return res;
	}
}
