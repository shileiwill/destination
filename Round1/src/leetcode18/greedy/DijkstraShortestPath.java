package leetcode18.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
// http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
public class DijkstraShortestPath {

	final List<Node> nodes;
	final List<Edge> edges;
	Set<Node> settledNodes = new HashSet<Node>();
	PriorityQueue<Node> unsettledNodes = new PriorityQueue<Node>(new Comparator<Node>(){
		public int compare(Node node1, Node node2) {
			return distance.get(node1) - distance.get(node2);
		}
	});
	
	Map<Node, Integer> distance = new HashMap<Node, Integer>(); // 距离source的距离
	Map<Node, Node> predecessor = new HashMap<Node, Node>(); // 目标是构建这个Map, 能够回溯出所有点到source的路径
	
	DijkstraShortestPath(Graph graph) {
		this.nodes = graph.nodes;
		this.edges = graph.edges;
		
		for (Node node : nodes) {
			distance.put(node, Integer.MAX_VALUE); // 默认距离是无穷大
		}
	}
	
	void execute(Node source) {
		distance.put(source, 0);
		unsettledNodes.offer(source);
		
		while (!unsettledNodes.isEmpty()) {
			Node now = unsettledNodes.poll();
			settledNodes.add(now); // Finalize this node, done
			updateMinDistanceInNeighbors(now);
		}
	}
	
	void updateMinDistanceInNeighbors(Node node) {
		List<Node> neighbors = findUnsettledNeighbors(node);
		
		for (Node target : neighbors) {
			int oldDist = distance.get(target);
			int newDist = distance.get(node) + getDistance(node, target); // What about going through the new settled node?!
			
			if (oldDist > newDist) { // Update
				distance.put(target, newDist); // Here is the only place we put and add. If old <= new, that means the distance is not MAX_VALUE, it is already put.
				predecessor.put(target, node);
				unsettledNodes.offer(target);
			}
		}
	}
	
	List<Node> findUnsettledNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edge edge : edges) {
			if (edge.src == node && !settledNodes.contains(node)) {
				neighbors.add(edge.dest); // Add the destination side
			}
		}
		return neighbors;
	}
	
	int getDistance(Node src, Node dest) {
		for (Edge edge : edges) {
			if (edge.src == src && edge.dest == dest) {
				return edge.weight;
			}
		}
		
		throw new IllegalArgumentException("Src and Dest are not connected");
	}
	
	// This is to print the shortest path of a given node
	List<Node> getPath(Node dest) {
		List<Node> path = new LinkedList<Node>();
		
		if (!predecessor.containsKey(dest)) {
			return path;
		}
		
		while (dest != null) {
			path.add(dest);
			dest = predecessor.get(dest);
		}
		
		return path;
	}
}

class Node {
	int val;
}

class Edge {
	Node src;
	Node dest;
	int weight;
}

class Graph {
	List<Node> nodes;
	List<Edge> edges;
}