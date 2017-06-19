package company.amazon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 有一堆EVENT, 每个EVENT记录WINNER和LOSER, 比如【A,B】表示A 是WINNER， B是LOSER, 判断是否存在A赢B， B赢C， C赢A 的情况
 */
public class GraphDFS {

	public static void main(String[] args) {

	}

	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();
		
		Node(String name) {
			this.name = name;
		}
	}
	
	boolean isLoop(List<String[]> list) {
		Collection<Node> graph = buildGraph(list);
		
		return dfs(graph);
	}
	
	

	private boolean dfs(Collection<Node> graph) {
		for (Node node : graph) {
			Set<Node> visited = new HashSet<Node>();
			visited.add(node);
			boolean res = helper(node, visited);
			if (res) {
				return true;
			}
			visited.remove(node);
		}
		
		return false;
	}



	private boolean helper(Node node, Set<Node> visited) {
		for (Node child : node.children) {
			if (visited.contains(child)) {
				return true;
			}
			
			visited.add(child);
			helper(child, visited);
			visited.remove(child);
		}
		return false;
	}



	private Collection<Node> buildGraph(List<String[]> list) {
		Map<String, Node> map = new HashMap<String, Node>();
		for (String[] pair : list) {
			String winner = pair[0];
			String loser = pair[1];
			
			Node winNode = map.get(winner);
			if (winNode == null) {
				winNode = new Node(winner);
			}
			
			Node loseNode = map.get(loser);
			if (loseNode == null) {
				loseNode = new Node(loser);
			}
			
			winNode.children.add(loseNode);
		}
		
		return map.values();
	}
	
	
}
