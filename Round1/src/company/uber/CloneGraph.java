package company.uber;

import java.util.HashMap;
import java.util.Map;

import company.uber.BipartiteGraph.Node;
// 133
public class CloneGraph {

	public static void main(String[] args) {

	}
	
	Map<Node, Node> map = new HashMap<Node, Node>();
	Node cloneGraph(Node node) {
		return dfs(node);
	}
	
	Node dfs(Node node) {
		if (node == null) {
			return null;
		}
		
		if (map.containsKey(node)) {
			return map.get(node);
		}
		
		Node copy = new Node(node.val);
		map.put(node, copy);
		
		for (Node child : node.children) {
			copy.children.add(dfs(child));
		}
		
		return copy;
	}
}
