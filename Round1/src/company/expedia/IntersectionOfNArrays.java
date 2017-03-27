package company.expedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfNArrays {

	public static void main(String[] args) {
		List<Integer> arr1 = new ArrayList<Integer>();
		List<Integer> arr2 = new ArrayList<Integer>();
		
		arr1.add(1);
		arr1.add(2);
		arr1.add(3);
		arr1.add(4);
		arr1.add(2);
		
		arr2.add(4);
		arr2.add(2);
		arr2.add(9);
		arr2.add(0);
		arr2.add(2);
		
		IntersectionOfNArrays in = new IntersectionOfNArrays();
		List<Integer> res = in.intersectionOf2ArraysMap(arr1, arr2);
		
		for (int val : res) {
			System.out.print(val + "--");
		}
	}

	List<Integer> intersectionOfArrays(List<List<Integer>> list) {
		List<Integer> res = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			List<Integer> now = list.get(i);
			
			List<Integer> next = intersectionOf2Arrays(res, now);
			res = next;
		}
		
		return res;
	}
	
	List<Integer> intersectionOf2Arrays(List<Integer> arr1, List<Integer> arr2) {
		List<Integer> res = new ArrayList<Integer>();
		
		Collections.sort(arr1);
		Collections.sort(arr2);
		
		int p1 = 0, p2 = 0;
		int index = 0;
		
		while (p1 < arr1.size() && p2 < arr2.size()) {
			int val1 = arr1.get(p1);
			int val2 = arr2.get(p2);
			
			if (val1 == val2) {
				//if (res.size() == 0 || res.get(res.size() - 1) != val1) {} If you dont want duplicate
				res.add(val1);
				p1++;
				p2++;
			} else if (val1 < val2) {
				p1++;
			} else {
				p2++;
			}
		}
		
		return res;
	}
	
	List<Integer> intersectionOf2ArraysMap(List<Integer> arr1, List<Integer> arr2) {
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		List<Integer> res = new ArrayList<Integer>();
		
		for (int val : arr1) {
			map1.put(val, map1.getOrDefault(val, 0) + 1);
		}
		
		for (int val : arr2) {
			if (map1.containsKey(val)) {
				// Add to Map2, with count
				map2.put(val, map2.getOrDefault(val, 0) + 1);
				// Remove from Map1
				map1.put(val, map1.get(val) - 1);
				if (map1.get(val) == 0) {
					map1.remove(val);
				}
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			
			for (int i = 0; i < value; i++) { // Remove this line if no duplicate allowed
				res.add(key);
			}
		}
		
		return res;
	}
}
