package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {

	public static void main(String[] args) {

	}

	boolean findOrder(int N, int[][] P) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> inDegree = new HashMap<Integer, Integer>();
		
		for (int[] edge : P) {
			int c1 = edge[0];
			int c2 = edge[1];
			
			if (!map.containsKey(c2)) {
				map.put(c2, new HashSet<Integer>());
			}
			
			map.get(c2).add(c1);
			inDegree.put(c1, inDegree.getOrDefault(c1, 0) + 1);
			if (!inDegree.containsKey(c2)) {
				inDegree.put(c2, 0);
			}
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
			int course = entry.getKey();
			int degree = entry.getValue();
			
			if (degree == 0) {
				queue.offer(course);
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			int now = queue.poll();
			res.add(now);
			
			if (map.containsKey(now)) {
				for (int course : map.get(now)) {
					inDegree.put(course, inDegree.get(course) - 1);
					
					if (inDegree.get(course) == 0) {
						queue.offer(course);
					}
				}
			}
		}
		
		return res.size() == N;
	}
}
