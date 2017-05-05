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
		
		Map<String, String> map1 = new HashMap<String, String>();
		// foo='123' bar=$foo * baz=$bar
		map1.put("foo", "123");
		map1.put("bar", "$foo");
		map1.put("abc", "456");
		map1.put("xyz", "$abc");
		map1.put("baz", "$bar");
		
//		m.topSort(map1);
		
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

	// 4. 字符串替换就是给你一个原字符串s，一个目标串p，一个替换字符串t，把s里面所有的p替换成t，比如给s="abcdefg", p="bc",
	// t="x"，返回"axdefg"
	// 面试官期望的是O(n)的解法，比如用一个sliding window记录当前窗口每个字符出现频率，只有出现频率符合目标串p里面字符频率才进行字符串比较，我说如果遇见"aaaaaa", 
	// 目标串为"aa",这种情况是会退化成O(n^2)的，面试官说是的，但是平均来说是O(n)
	String replaceString(String original, String from, String to) {
		if (original.length() < from.length() || from.equals(to)) {
			return original;
		}

		int[] count = new int[26];
		for (char c : from.toCharArray()) {
			count[c - 'a']++;
		}

		int[] curCount = new int[26]; // 记录个数
		int left = 0, right = 0;
		while (right < original.length()) {
			if (right - left < from.length()) {
				curCount[original.charAt(right) - 'a']++;
			} else {
				boolean isSame = true;
				for (int i = 0; i < 26; i++) {
					if (count[i] != curCount[i]) {
						isSame = false;
					}
				}

				if (isSame && original.substring(left, right).equals(from)) { // 比较顺序
					original = original.substring(0, left) + to + original.substring(right);
					Arrays.fill(curCount, 0);
					right = left; // Weird
					continue;
				}

				count[original.charAt(left) - 'a']--;
				left++;
				count[original.charAt(right) - 'a']++;
			}

			right++;
		}

		return original;
	}

	/**
	 * 给定某应用的默认配置和期望配置，合并这两个配置文件，期望配置可以覆盖默认配置，如果期望配置里面没有的就使用默认配置（其实就是合并两个字典）；
	 * follow-up是如果配置文件里面的变量可以相互引用改怎么处理，比如 foo='123' bar=$foo
	 * baz=$bar，需要写程序来解析并生成最终配置文件
	 * 第三轮就是把问题转换成拓扑排序，如果某个变量依赖于其他变量，就创建一条其他变量指向它的边，先把入度为0的放进队列，然后逐个处理依赖队首元素的点。当然也能拿DFS来做。
	 * 
	 * 真牛，这题都能想到拓扑排序！
	 */

	void merge(Map<String, String> map1, Map<String, String> map2) {
		for (Map.Entry<String, String> entry : map2.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			map1.put(key, value); // Just override or add
		}
		// Done with first question
	}

	void topSort(Map<String, String> map1) {
		// Topological Sort
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, Integer> degree = new HashMap<String, Integer>();
		Queue<String> queue = new LinkedList<String>();

		for (Map.Entry<String, String> entry : map1.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (value.startsWith("$")) {
				value = value.substring(1, value.length());

				if (!map.containsKey(value)) {
					map.put(value, new ArrayList<String>());
				}

				map.get(value).add(key);
				degree.put(key, degree.getOrDefault(key, 0) + 1);
			} else {
				degree.put(key, 0);
				queue.offer(key);
			}
		}

		while (!queue.isEmpty()) {
			String now = queue.poll();
			System.out.println(now + "=========" + map1.get(now));

			if (map.containsKey(now)) {
				for (String next : map.get(now)) {
					map1.put(next, map1.get(now));
					
					degree.put(next, degree.get(next) - 1);
					
					if (degree.get(next) == 0) {
						queue.offer(next);
					}
				}
			}
		}
	}
}
