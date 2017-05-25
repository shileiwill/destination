package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MergeProperties {

	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<String, String>();
		// foo='123' bar=$foo * baz=$bar
		map1.put("foo", "123");
		map1.put("bar", "$foo");
		map1.put("abc", "456");
		map1.put("xyz", "$abc");
		map1.put("baz", "$bar");
		
//		m.topSort(map1);
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

			if (map.containsKey(now)) { // 有人依赖他
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
