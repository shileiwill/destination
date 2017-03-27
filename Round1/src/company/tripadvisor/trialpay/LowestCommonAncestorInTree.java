package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.List;

// Tree is not binary tree
public class LowestCommonAncestorInTree {

	public static void main(String[] args) {
		Node n3 = new Node(3);
		Node n9 = new Node(9);
		Node n20 = new Node(20);
		Node n15 = new Node(15);
		Node n7 = new Node(7);
		Node n31 = new Node(31);
		Node n91 = new Node(91);
		Node n210 = new Node(210);
		Node n115 = new Node(115);
		Node n17 = new Node(17);
		
		List<Node> list1 = new ArrayList<Node>();
		list1.add(n9);
		list1.add(n20);
		list1.add(n15);
		n3.children = list1;
		
		List<Node> list2 = new ArrayList<Node>();
		list2.add(n7);
		list2.add(n31);
		n9.children = list2;
		
		List<Node> list3 = new ArrayList<Node>();
		list3.add(n91);
		n20.children = list3;
		
		List<Node> list4 = new ArrayList<Node>();
		list4.add(n210);
		list4.add(n17);
		list4.add(n115);
		n15.children = list4;
		
		LowestCommonAncestorInTree lca = new LowestCommonAncestorInTree();
		Node res = lca.findLowestCommonAncestor(n3, n7, n31);
		System.out.println(res.val);
	}

	Node findLowestCommonAncestor(Node root, Node n1, Node n2) {
		if (root == null || root == n1 || root == n2) {
			return root;
		}
		
		List<Node> res = new ArrayList<Node>();
		
		if (root.children == null) {
			return null;
		}
		
		for (Node child : root.children) {
			Node next = findLowestCommonAncestor(child, n1, n2);
			res.add(next);
		}
		
		boolean allNull = true;
		boolean allNotNull = true;
		Node notNullNode = null;
		int countOfNotNull = 0;
		for (Node next : res) {
			if (next == null) {
				allNotNull = false;
			} else {
				notNullNode = next;
				allNull = false;
				countOfNotNull++;
			}
		}
		
		if (allNull) {
			return null;
		}
		
		if (allNotNull) {
			return root;
		}
		
		if (countOfNotNull == 1) {
			return notNullNode;
		} else {
			return root; // Found the 2 nodes on 2 children
		}
	}
}

class Node {
	int val;
	List<Node> children = null;
	
	Node(int val) {
		this.val = val;
	}
}