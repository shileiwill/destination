package amazon.new_03_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PathInGraph {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n1.neighbors.add(n4);
		
		n2.neighbors.add(n5);
		n2.neighbors.add(n6);
		
		n3.neighbors.add(n4);
		
		n5.neighbors.add(n1);
		
		n4.neighbors.add(n6);
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		list.add(n1.val);
		visited.add(n1.val);
//		dfs(res, list, n1, n6, visited);
		bfs(n1, n6);
		
		for (List<Integer> l : res) {
			for (int val : l) {
				System.out.print(val + " -- ");
			}
			System.out.println();
		}
	}
	
	static List<List<Integer>> bfs(Node src, Node dest) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new LinkedList<Integer>();
		Set<Node> visited = new HashSet<Node>();
		
		if (src == dest) {
			list.add(src.val);
			res.add(list);
			return res;
		}

		int level = 1;
		Map<Node, Node> map = new HashMap<Node, Node>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(src);
		visited.add(src);
		
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Node now = queue.poll();
				
				for (Node next : now.neighbors) {
					if (next == dest) {
//						res.add(new ArrayList<Integer>(list));
						map.put(dest, now);
						System.out.println(level + 1);
						break;
					}
					
					if (!visited.contains(next)) {
						visited.add(next);
						map.put(next, now);
						queue.offer(next);
					}
				}
			}
			
			level++;
		}
		
		if (!map.containsKey(dest)) {
			System.out.println("No Such Route");
			return null;
		}
		
		while (map.get(dest) != src) {
			list.add(0, dest.val);
			dest = map.get(dest);
		}
		
		list.add(0, dest.val);
		list.add(0, src.val);
		
		for (int val : list) {
			System.out.print(val + "--");
		}
		return res;
	}
	static void dfs(List<List<Integer>> res, List<Integer> list, Node src, Node dest, Set<Integer> visited) {
		if (src == dest) {
//			list.add(dest.val);
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (Node node : src.neighbors) {
			if (!visited.contains(node.val)) {
				list.add(node.val);
				visited.add(node.val);
				dfs(res, list, node, dest, visited);
				visited.remove(node.val);
				list.remove(list.size() - 1);
			}
		}
	}

}

class Node {
	int val;
	Set<Node> neighbors = new HashSet<Node>();
	
	Node(int val) {
		this.val = val;
	}
}