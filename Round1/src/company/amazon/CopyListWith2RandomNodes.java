package company.amazon;

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
		copyToMap(head);
		System.out.println("head : " + head.label); // Still the head, dont worry. The above method will not change head
		mapToList();
		return map.get(head);

		// http://www.programcreek.com/2013/09/string-is-passed-by-reference-in-java/
		// http://www.programcreek.com/2011/08/so-java-passes-object-by-reference-or-by-value/
//		test(head); // This will actually change the head
//		System.out.println("Is head changed? YES: " + head.label);
//		return null;
	}
	
	void test(Node node) {
		node.label = 100;
	}
	
	// DFS all nodes and put to Map
	void copyToMap(Node node) {
		if (node == null) {
			return;
		}
		
		if (node.ran1 != null && !visited.contains(node.ran1)) { // Not null && not visited yet
			Node ran1 = node.ran1;
			visited.add(ran1);
			map.put(ran1, new Node(ran1.label));
			copyToMap(ran1); // DFS
		}
		
		if (node.ran2 != null && !visited.contains(node.ran2)) { // Not null && not visited yet
			Node ran2 = node.ran2;
			visited.add(ran2);
			map.put(ran2, new Node(ran2.label));
			copyToMap(ran2);
		}
	}
	
	void mapToList() {
		for (Map.Entry<Node, Node> entry : map.entrySet()) {
			Node originalNode = entry.getKey();
			Node copiedNode = entry.getValue();
			
			copiedNode.ran1 = map.get(originalNode.ran1);
			copiedNode.ran2 = map.get(originalNode.ran2);
		}
	}
	
	Node deepCopy2(Node node) {
		Set<Node> visited2 = new HashSet<Node>();
		Map<Node, Node> map2 = new HashMap<Node, Node>(); 

		Node head1 = node;
		while (head1 != null) {
			Node ran1 = head1.ran1;
			if (!visited.contains(ran1)) {
				visited.add(ran1);
				map.put(ran1, new Node(ran1.label));
				head1 = ran1;
			} else { // When to stop? Bug here!!!
				break;
			}
			
		}
		
		Node head2 = node;
		while (head2 != null) {
			Node ran2 = head2.ran2;
			if (!visited.contains(ran2)) {
				visited.add(ran2);
				map.put(ran2, new Node(ran2.label));
				head2 = ran2;
			}  else {
				break;
			}
		}
		
		// Above, All nodes are visited and put to map
		
		head1 = node;
		while (head1 != null) {
			Node ran1 = head1.ran1;
			if (ran1 != null) {
				map.get(head1).ran1 = map.get(head1.ran1);
			}
			head1 = ran1;
		}
		
		head2 = node;
		while (head2 != null) {
			Node ran2 = head2.ran2;
			if (ran2 != null) {
				map.get(head2).ran2 = map.get(head2.ran2);
			}
			head2 = ran2;
		}
		
		return map.get(node);
	}

}

class Node {
     int label;
     Node ran1, ran2;
     Node(int x) { this.label = x; }
};