package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	/**
	 * TreeSet: This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains)
	 */
	
	// NLog(N)
	List<int[]> findUniquePairs1(int[] arr, int target) {
		Arrays.sort(arr);;
		List<int[]> res = new ArrayList<int[]>();
		
		int left = 0, right = arr.length - 1;
		
		while (left < right) {
			int sum = arr[left] + arr[right];
			
			if (sum == target) {
				int[] pair = {arr[left], arr[right]};
				res.add(pair);
				
				left++;
				while (left < right && arr[left] == arr[left - 1]) {
					left++;
				}
				
				right--;
				while (left < right && arr[right] == arr[right + 1]) {
					right--;
				}
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		
		return res;
	}
	
	// O(N)
	List<int[]> findUniquePairs2(int[] arr, int target) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		List<int[]> res = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], new HashSet<Integer>());
			}
			map.get(arr[i]).add(i);
		}
		
		for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
			int val = entry.getKey();
			Set<Integer> set = entry.getValue();
			
			int toFind = target - val;
			if (toFind == val) {
				if (set.size() >= 2) {
					res.add(new int[]{toFind, toFind});
				}
			} else {
				if (map.containsKey(toFind) && !visited.contains(toFind)) {
					res.add(new int[]{toFind, val});
					visited.add(toFind);
					// Remember to remove the other side, toFind as key
					// map.remove(toFind); This will give concurrentModification Exception
				}
			}
			visited.add(val);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		Design2Sum addFaster = new Design2Sum();
		
		int[] arr = {3, 4, 5, 1, 3, 8, 6, 2, 2, 1};
		int target = 6;
		List<int[]> res = addFaster.findUniquePairs2(arr, target);
		
		for (int[] pair : res) {
			System.out.println(pair[0] + "====" + pair[1]);
		}
//		addFaster.add(2);
//		System.out.println("1 : " + addFaster.find(5));
//		addFaster.add(5);
//		System.out.println("2 : " + addFaster.find(12));
//		addFaster.add(3);
//		System.out.println("3 : " + addFaster.find(5));
//		addFaster.add(7);
//		System.out.println("4 : " + addFaster.find(9));
//		addFaster.add(4);
//		System.out.println("5 : " + addFaster.find(15));
//		addFaster.add(12);
//		System.out.println("6 : " + addFaster.find(10));
//		addFaster.add(5);
//		System.out.println("7 : " + addFaster.find(10));
//		addFaster.add(8);
//		System.out.println("8 : " + addFaster.find(5));
	}
}

class Design2SumFindFaster {
	List<Integer> source = new ArrayList<Integer>();
	Set<Integer> sum = new HashSet<Integer>();
	
	void add(int val) {
		if (source.isEmpty()) {
			source.add(val);
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