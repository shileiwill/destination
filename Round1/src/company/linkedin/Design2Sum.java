package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Add faster
public class Design2Sum {
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	void add(int val) {
		map.put(val, map.getOrDefault(val, 0) + 1);
	}
	
	boolean find(int target) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int value = entry.getKey();
			int count = entry.getValue();
			
			int toFind = target - value;
			if (toFind == value) {
				if (count >= 2) {
					return true;
				}
			} else {
				if (map.containsKey(toFind)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Design2SumFindFaster addFaster = new Design2SumFindFaster();
		addFaster.add(2);
		System.out.println("1 : " + addFaster.find(5));
		addFaster.add(5);
		System.out.println("2 : " + addFaster.find(12));
		addFaster.add(3);
		System.out.println("3 : " + addFaster.find(5));
		addFaster.add(7);
		System.out.println("4 : " + addFaster.find(9));
		addFaster.add(4);
		System.out.println("5 : " + addFaster.find(15));
		addFaster.add(12);
		System.out.println("6 : " + addFaster.find(10));
		addFaster.add(5);
		System.out.println("7 : " + addFaster.find(10));
		addFaster.add(8);
		System.out.println("8 : " + addFaster.find(5));
	}
}

class Design2SumFindFaster {
	List<Integer> source = new ArrayList<Integer>();
	Set<Integer> sum = new HashSet<Integer>();
	
	void add(int val) {
		if (source.isEmpty()) {
			source.add(val);
			return;
		} else {
			for (int num : source) {
				sum.add(num + val);
			}
			source.add(val);
		}
	}
	
	boolean find(int target) {
		return sum.contains(target);
	}
}