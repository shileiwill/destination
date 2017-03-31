package company.tripadvisor.trialpay;

import java.util.HashSet;
import java.util.Set;

import chapter3.binaryTree.TreeNode;

public class LCAWithParentNode {

	public static void main(String[] args) {
		NodeP n2 = new NodeP(2);
		NodeP n5 = new NodeP(5);
		NodeP n4 = new NodeP(4);
		NodeP n3 = new NodeP(3);
		NodeP n1 = new NodeP(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		n1.parent = n3;
		n3.parent = n4;
		n4.parent = n2;
		n5.parent = n2;
		
		LCAWithParentNode lca = new LCAWithParentNode();
		NodeP parent = lca.LCA(n1, n3);
		
		System.out.println(parent.val);
	}
	
	NodeP LCA(NodeP node1, NodeP node2) {
		Set<NodeP> set = new HashSet<NodeP>();
		
		while (node1 != null && node2 != null) {
			if (set.contains(node1)) {
				return node1;
			}
			if (set.contains(node2)) {
				return node2;
			}
			
			set.add(node1);
			set.add(node2);
			node1 = node1.parent;
			node2 = node2.parent;
		}
		
		while (node1 != null) {
			if (set.contains(node1)) {
				return node1;
			}
			
			set.add(node1);
			node1 = node1.parent;
		}
		
		while (node2 != null) {
			if (set.contains(node2)) {
				return node2;
			}
			
			set.add(node2);
			node2 = node2.parent;
		}
		
		return null;
	}
}

class NodeP {
	NodeP left;
	NodeP right;
	NodeP parent;
	int val;
	
	NodeP(int val) {
		this.val = val;
	}
	
}
