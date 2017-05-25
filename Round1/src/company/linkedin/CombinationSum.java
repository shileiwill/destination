package company.linkedin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Given a target and an array, return the number of combinations from the array that can add to the given number 写了之后他说array里可以有负数，
于是稍微改了一下就好
 */
public class CombinationSum {

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] arr = {5, 1};
		int target = 6;
		System.out.println(cs.dp(arr, target));
	}

	int dp(int[] arr, int target) {
		int[] hash = new int[target + 1];
		hash[0] = 1;
		
		for (int i = 1; i <= target; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i - arr[j] >= 0) {
					hash[i] += hash[i - arr[j]];
				}
			}
		}
		
		return hash[target]; // This includes 1, 1, 1, 1, 1, 1
	}
	
	int res = 0;
	int combination(int[] arr, int target) {
		Arrays.sort(arr);
		helper(arr, target, 0);
		return res;
	}
	
	void helper(int[] arr, int target, int pos) {
		if (pos == arr.length) {
			return;
		}
		
		if (target == 0 && pos != 0) {
			res++;
//			return;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			helper(arr, target - arr[i], i + 1);
		}
	}
	
	// Can use Map to memorize. 数组是排好序的，以一个position为起点的target sum是固定个数的
	int helper(int[] arr, int target, int pos, Map<Integer, Map<Integer, Integer>> map) {
		if (map.containsKey(target) && map.get(target).containsKey(pos)) {
			return map.get(target).get(pos);
		}
		
		int count = 0;
		if (!map.containsKey(target)) {
			map.put(target, new HashMap<>());
		}
		
		if (target == 0 && pos != 0) {
			count++;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			count += helper(arr, target - arr[i], i + 1, map);
		}
		
		map.get(target).put(pos, count);
		return count;
	}
}
