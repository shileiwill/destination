package company.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chapter4.linkedlist.CopyListWithRandomPointer;

public class CopyListWith2RandomNodes {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.ran1 = n2;
		n1.ran2 = n1;
		
		n2.ran1 = n3;
		n3.ran1 = n4;
		n4.ran1 = n5;
		n5.ran1 = n3;
		
		n2.ran2 = n5;
		n3.ran2 = null;
		n4.ran2 = n2;
		n5.ran2 = n1;
		
		Node res = new CopyListWith2RandomNodes().deepCopy(n1);
		System.out.println(res.label);
	}
	
	Set<Node> visited = new HashSet<Node>();
	Map<Node, Node> map = new HashMap<Node, Node>();
	
	Node deepCopy(Node head) {
		map.put(head, new Node(head.label));
		visited.add(head);
		copyToMapMyStyle(head);
		System.out.println("head : " + head.label); // Still the head, dont worry. The above method will not change head
		mapToList();
		return map.get(head);
	}
	
	void copyToMapMyStyle(Node node) { // This is far better
		if (node == null || visited.contains(node)) {
			return;
		}
		
		visited.add(node);
		map.put(node, new Node(node.label));
		copyToMapMyStyle(node.ran1); // DFS
		copyToMapMyStyle(node.ran2);
	}
	
	void mapToList() {
		for (Map.Entry<Node, Node> entry : map.entrySet()) {
			Node originalNode = entry.getKey();
			Node copiedNode = entry.getValue();
			
			copiedNode.ran1 = map.get(originalNode.ran1);
			copiedNode.ran2 = map.get(originalNode.ran2);
		}
	}
	
	static class Node {
	     int label;
	     Node ran1, ran2;
	     Node(int x) { this.label = x; }
	};
}
