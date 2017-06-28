package company.facebook;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// This class is not tested
// 无向图上两点最短距离。写了bfs，小哥不满意空间复杂度，又写了dfs，小哥不满意时间复杂度，想了想又写了双向bfs，小哥挺满意。
// 然后瞎聊followup，说有重量怎么办，我又精神抖擞写了个dijkstra，觉得还行，没辜负自己辛苦刷题。
public class ShortestPathInUndirectedGraph {

	public static void main(String[] args) {

	}

	/**
	 * 因为一直在remove 所以不用visited set
	 * You can also use 2 way BFS
	 * @param map
	 * @param start
	 * @param end
	 * @return
	 */
	int shortestPathBFS(Map<Node, Set<Node>> map, Node start, Node end) {
		if (start == end) {
			return 0;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(start);
		
		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				
				if (node == end) {
					return level + 1;
				}
				
				if (map.containsKey(node)) {
					for (Node child : map.get(node)) {
						map.get(child).remove(node);
						queue.offer(child);
					}
					
					map.remove(node);
				}
			}
			
			level++;
		}
		
		return -1;
	}
	
	int res = Integer.MAX_VALUE;
	int shortestPathDFS(Map<Node, Set<Node>> map, Node start, Node end) {
		if (start == end) {
			return 0;
		}
		
		Set<Node> visited = new HashSet<Node>();
		helper(map, start, end, visited, 1);
		
		return res;
	}
	
	void helper(Map<Node, Set<Node>> map, Node start, Node end, Set<Node> visited, int level) {
		if (start == end) {
			res = Math.min(res, level);
			return;
		}
		
		if (map.containsKey(start)) {
			for (Node child : map.get(start)) {
				if (!visited.contains(child)) {
					visited.add(child);
					helper(map, child, end, visited, level + 1);
					visited.remove(child);
				}
			}
		}
	}
}
