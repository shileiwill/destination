package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * var staffs = 
 * { 1: 
 * 		{ ‘title’: ‘CEO’, 
 * 		  ‘id’: 1, 
 * 	      ‘reports_to’: null, 
 * 		  ‘children’: , 
 * 		  ‘importance’: 10 
 * 	    }, 
 *   2: 
 *   	{ ‘title’: ‘VP Marketing’, 
 *   	  ‘id’: 2, 
 *   	  ‘reports_to’: 1, 
 *   	  ‘children’: , 
 *   	  ‘importance’: 4 
 *      }, 
 *   3: { ‘title’: ‘staff engineer’, ‘id’: 3, ‘reports_to’: 1, ‘children’: , ‘importance’: 3, }, 
 *   4: { ‘title’: ‘senior engineer’, ‘id’: 4, ‘reports_to’: 3, ‘children’: , ‘importance’: 2 } 
 * }; 
 * 设计一个sum函数求特定employee的importances(包含下属和自己) sum(1) = 19 sum(3) = 5
 */
public class StaffImportance {

	public static void main(String[] args) {
//		List<Node> staffs = JSON.parse(args);
		List<Node> staffs = new ArrayList<Node>();
		Node node1 = new Node("CEO1", 1, null, 10);
		Node node2 = new Node("CEO2", 2, 1, 4);
		Node node3 = new Node("CEO3", 3, 1, 3);
		Node node4 = new Node("CEO4", 4, 3, 2);
		
		staffs.add(node1);
		staffs.add(node2);
		staffs.add(node3);
		staffs.add(node4);
		
		
//		Map<Integer, Integer> degree = new HashMap<Integer, Integer>(); // No need of this degree, other than root, all others' have degree 1
		// Use topological sorting to find out all dependencies
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Node> cache = new HashMap<Integer, Node>();
		
		Node root = null;
		for (Node node : staffs) {
			int self = node.id;
			Integer boss = node.reportTo;
			
			if (boss == null) {
				root = node;
			} else {
				if (!map.containsKey(boss)) {
					map.put(boss, new HashSet<Integer>());
				}
				map.get(boss).add(self);
			}
			
			cache.put(self, node);
		}
		
		// Build N-ary tree
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (map.containsKey(node.id)) {
				for (int child : map.get(node.id)) {
					Node childNode = cache.get(child);
					
					node.children.add(childNode);
					queue.offer(childNode);
				}
			}
		}
		
		// Get Sum
		int sum = getSum(cache.get(3));
		System.out.println(sum);
	}
	
	static int getSum(Node node) {
		if (node == null) {
			return 0;
		}
		
		int sum = node.importance;
		
		for (Node child : node.children) {
			sum += getSum(child);
		}
		
		return sum;
	}

	static class Node {
		String title;
		int id;
		Integer reportTo;
		int importance;
		Set<Node> children = null;
		
		public Node(String title, int id, Integer reportTo, int importance) {
			this.children = new HashSet<Node>();
			this.title = title;
			this.id = id;
			this.reportTo = reportTo;
			this.importance = importance;
		}
	}
}
