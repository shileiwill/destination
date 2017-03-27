package company.expedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortByFrequency {

	public static void main(String[] args) {
		int[] arr = {3, 1, 2, 2, 4};
		SortByFrequency sort = new SortByFrequency();
		int[] res = sort.customSort(arr);
		
		for (int val : res) {
			System.out.print(val + "--");
		}
	}

	int[] customSort(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int len = arr.length;
		int[] res = new int[len];
		
		for (int val : arr) {
			map.put(val, map.getOrDefault(val, 0) + 1);
		}
		
		Comparator<Map.Entry<Integer, Integer>> com = new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
				int key1 = entry1.getKey();
				int key2 = entry2.getKey();
				int val1 = entry1.getValue();
				int val2 = entry2.getValue();
				
				if (val1 != val2) {
					return val1 - val2;
				}
				
				return key1 - key2;
			}
		};
		
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(set);
		Collections.sort(list, com);
		
		int index = 0;
		for (Map.Entry<Integer, Integer> entry : list) {
			int key = entry.getKey();
			int count = entry.getValue();
					
			for (int i = 0; i < count; i++) {
				res[index++] = key;
			}
		}
		
		return res;
	}
}
