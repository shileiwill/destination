package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctNumbersInK {

	public static void main(String[] args) {
		Integer[] arr = {2, 7, 7, 81, 81};
		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(arr));
		List<Integer> res = DistinctNumbersInK.distinctElements(A, 2);
		
		for (int val : res) {
			System.out.println(val);
		}
	}
	
	// 以K个元素为一组，判断里边有多少distinct elements.
	public static List<Integer> distinctElements(List<Integer> list, int K) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		int left = 0;
		int right = 0;
		
		while (right < K && right < list.size()) {
			int num = list.get(right);
			map.put(num, map.getOrDefault(num, 0) + 1);
			right++;
		}
		
		if (right < K) {
			return res;
		}
		
		res.add(map.size()); // First K
		while (right < list.size()) {
			int leftVal = list.get(left);
			int rightVal = list.get(right);
			
			map.put(leftVal, map.get(leftVal) - 1);
			if (map.get(leftVal) == 0) {
				map.remove(leftVal);
			}
			
			map.put(rightVal, map.getOrDefault(rightVal, 0) + 1);
			res.add(map.size());
			
			left++;
			right++;
		}
		
		return res;
	}
}
