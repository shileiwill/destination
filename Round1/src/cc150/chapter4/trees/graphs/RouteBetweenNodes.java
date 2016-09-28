package cc150.chapter4.trees.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RouteBetweenNodes {

	public static void main(String[] args) {

	}

	boolean search(Node n1, Node n2) {
		Queue<Node> queue = new LinkedList<Node>();
		List<Node> visited = new ArrayList<Node>();
		
		visited.add(n1);
		queue.offer(n1);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node == n2) {
				return true;
			}
			for (Node neighbor : node.children) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}
		
		return false;
	}
}

class Graph {
	public Node[] nodes;
}
class Node {
	public String name;
	public Node[] children;
}