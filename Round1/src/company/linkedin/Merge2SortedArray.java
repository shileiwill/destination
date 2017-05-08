package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1. 找出一个数组里面只出现过一次的数字； 2. 合并两个有序数组； 3. 合并K个有序数组。
 */
public class Merge2SortedArray {

	public static void main(String[] args) {
		Merge2SortedArray m = new Merge2SortedArray();
//		int[] arr = { 3, 2, 9, 7, 9, 6, 3, 2 };
//		int res = m.findOnce(arr);
//		System.out.println(res);
		
		int[] arr = {3, 5, 1, 7, 1, 4, 8, 2, 3, 4, 1, 6, 9};
		m.find3Sequential(arr, 12);
	}

	void find3Sequential(int[] arr, int target) {
		int sum = 0;
		
		if (arr.length < 3) {
			return;
		}
		
		sum = arr[0] + arr[1] + arr[2];
		
		for (int i = 3; i < arr.length; i++) {
			if (sum == target) {
				System.out.println(arr[i - 3] + "==" + arr[i - 2] + "==" + arr[i - 1]);
			}
			
			sum += arr[i] - arr[i - 3];
		}
	}
	
	int findOnce(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int val : arr) {
			map.put(val, map.getOrDefault(val, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}

		return -1;
	}
}
