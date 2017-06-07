package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 现在有一堆国家1...n 他们要打一场世界大战 世界大战的条件如下
1. 所有的国家都要加入世界大战
2. 世界大战只有两方的敌对势力Group A和Group B.
3. Group A或者Group B之内不能互相战争
现在给一个国家之间的战争列表 e.g.
1 -> 2,3,4
（意味着1同时要和2,3,4打仗）
（面试官挖的坑，很后来才问出来，注意，这同时也意味着2要和1打仗,3要和1打仗,4要和1打仗）
2 -> 1,5
6 -> 7
etc...
问可不可能会发生世界大战 返回true or false

死活想不出是graph的bfs graph都自己在白板上画出来了 bfs都自己过出来了一遍就是想不到bfs 45分钟基本都在自我纠结linear扫过这个列表的时候 6到底应该assign group A还是group B
最后面试官看不下去了 随便给了一道bst inorder traversal 再随便给了道graph的bfs 然后面试官问再看看这道题...
 */
public class BipartiteGraph {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node8 = new Node(8);
		
		node1.children.add(node2);
		node1.children.add(node3);
		
		node2.children.add(node6);
		node6.children.add(node5);
		node5.children.add(node6);
//		node4.children.add(node3);
		
		List<Node> list = new ArrayList<Node>();
		list.add(node1);
		boolean res = new BipartiteGraph().isGraphBipartite(list);
		System.out.println(res);
		
//		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
//		graph.put(1, new HashSet<Integer>());
//		graph.put(2, new HashSet<Integer>());
//		graph.put(3, new HashSet<Integer>());
//		graph.put(4, new HashSet<Integer>());
//		graph.put(5, new HashSet<Integer>());
//		
//		Integer[] arr1 = {2, 3, 4, 5};
//		graph.get(1).addAll(Arrays.asList(arr1));
//		
//		graph.get(2).add(1);
//		graph.get(2).add(5);
//		
//		graph.get(3).add(1);
//		graph.get(4).add(1);
//		
//		graph.get(5).add(2);
//		graph.get(5).add(1);
//		
//		boolean res = new BipartiteGraph().isGraphBipartite(graph);
//		System.out.println(res);
	}

	static class Node {
		int val;
		List<Node> children = new ArrayList<Node>();
		
		Node(int val) {
			this.val = val;
		}
	}
	
	// Using Map is better, easier
	// If the graph has more than 1 independent parts, this Map couldn't cover all.
	boolean isGraphBipartite(Map<Integer, Set<Integer>> graph) {
		Set<Integer> group1 = new HashSet<Integer>();
		Set<Integer> group2 = new HashSet<Integer>();
		boolean isGroup1 = true;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(graph.keySet().iterator().next());
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Integer now = queue.poll();
				
				if (isGroup1) {
					if (group2.contains(now)) {
						return false;
					}
					group1.add(now);
				} else {
					if (group1.contains(now)) {
						return false;
					}
					group2.add(now);
				}
				
				for (Integer child : graph.get(now)) {
					queue.offer(child);
					graph.get(child).remove(now); // Remove the other side
				}

				graph.remove(now); // 直接从Map中移除当前node
			}
			isGroup1 = !isGroup1;
		}
		
		return true;
	}
	
	boolean isGraphBipartite(List<Node> graph) {
		Set<Node> group1 = new HashSet<Node>();
		Set<Node> group2 = new HashSet<Node>();
		boolean isGroup1 = true;
		
//		Set<Node> visited = new HashSet<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		for (Node node : graph) {
//			visited.add(node);
			queue.offer(node);
		}
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Node now = queue.poll();
				
				if (isGroup1) {
					if (group2.contains(now)) {
						return false;
					}
					group1.add(now);
				} else {
					if (group1.contains(now)) {
						return false;
					}
					group2.add(now);
				}
				
//				if (!visited.contains(now)) {
					for (Node child : now.children) {
//						if (!visited.contains(child)) {
//							visited.add(child);
							queue.offer(child);
//						}
					}
//				}
			}
			
			isGroup1 = !isGroup1;
		}
		
		return true;
	}
}
