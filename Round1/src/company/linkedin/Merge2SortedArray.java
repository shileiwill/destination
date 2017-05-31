package company.linkedin;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 找出一个数组里面只出现过一次的数字； 2. 合并两个有序数组； 
 * 
 * 3. 合并K个有序数组
 * 之后又问了一下时间复杂度, Should be N * KLog(K)
 * 
 * follow up 1，
 * 如果数据太大了放不下怎么办？答曰：输入输出都放文件，内存就放一个priority queue 
 * follow up 2：
 * 万一大到连priority queue也放不下怎么办？答曰：那就得用多个queue，两两merge，然后问了复杂度。 
 * follow up 3：
 * 如果再得提高速度怎么办。答曰：因为两两merge时不会相互影响，可以并行着处理。
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
