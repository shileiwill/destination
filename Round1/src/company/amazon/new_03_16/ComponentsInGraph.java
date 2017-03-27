package company.amazon.new_03_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComponentsInGraph {
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n1.neighbors.add(n4);
		
		n2.neighbors.add(n5);
		n2.neighbors.add(n6);
		
		n3.neighbors.add(n4);
		
		n5.neighbors.add(n1);
		
		n4.neighbors.add(n6);
		
		List<Node> graph = new ArrayList<Node>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		graph.add(n5);
		graph.add(n6);
		graph.add(n7);
		graph.add(n8);
		
		Set<Node> visited = new HashSet<Node>();
		int count = 0;
//		List<Node> res = topSort(graph);
		for (Node node : graph) {
			if (!visited.contains(node)) {
				count++;
				dfs(node, visited);
			}
			
//			System.out.println();
		}
		System.out.print(count + " -- ");
		
	}
	
	static void dfs(Node node, Set<Node> visited) {
		for (Node nei : node.neighbors) {
			if (!visited.contains(nei)) {
				visited.add(nei);
				dfs(nei, visited);
			}
		}
	}
	
	// This is not accurate if there is circle.
	static List<Node> topSort(List<Node> graph) {
		Map<Node, Integer> inDegree = new HashMap<Node, Integer>();
		
		for (Node node : graph) {
			for (Node nei : node.neighbors) {
				if (!inDegree.containsKey(nei)) {
					inDegree.put(nei, 0);
				}
				inDegree.put(nei, inDegree.get(nei) + 1);
			}
		}
		
		List<Node> leaders = new ArrayList<Node>();
		for (Node node : graph) {
			if (!inDegree.containsKey(node)) {
				leaders.add(node);
			}
		}
		
		return leaders;
	}
}
