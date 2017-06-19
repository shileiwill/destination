package company.facebook;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

// Tree is not binary tree
public class LowestCommonAncestorInNaryTree {

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
		
		LowestCommonAncestorInNaryTree lca = new LowestCommonAncestorInNaryTree();
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
		
		Node notNullNode = null;
		int countOfNotNull = 0;
		for (Node next : res) {
			if (next != null) {
				notNullNode = next;
				countOfNotNull++;
			}
		}
		
		if (countOfNotNull == 0) {
			return null;
		} else if (countOfNotNull == 1) {
			return notNullNode;
		} else {
			return root; // Found the 2 nodes on 2 children
		}
	}
	
	/**
	 * 第一题问最低公共节点。LC236。 然后是问最深节点的最低公共节点。不一定是Binary Tree，做出来了面试官没说有什么问题然后时间到了让分析一下复杂度。
	 * 跟二叉树一样，不用先求深度，可以one pass。   对于root，每个孩子遍历一遍，如果孩子深度大于max，就更新max，然后返回的是孩子，如果等于max，说明有多个孩子深度一样，那么返回root
	 * 
	 * Brute force is to find the lowest nodes first, and then find common ancestor
	 */
	public Result deepestRoot(TreeNode node) {
		if (node == null) {
			return new Result(0, null);
		}
		
		Result left = deepestRoot(node.left);
		Result right = deepestRoot(node.right);
		
		Result res = new Result();
		if (left.depth == right.depth) { //两边一样深, node是公共
			res.depth = left.depth + 1;
			res.root = node;
		} else if (left.depth > right.depth) { //左边深
			res.depth = left.depth + 1;
			res.root = left.root;
		} else {
			res.depth = right.depth + 1;
			res.root = right.root;
		}
		
		return res;
	}
	
	public Result deepestRoot(Node node) {
		if (node == null) {
			return new Result(0, null);
		}
		
		List<Result> res = new ArrayList<Result>();
		
		if (node.children == null) {
			return null;
		}
		
		for (Node child : node.children) {
			Result r = deepestRoot(child);
			res.add(r);
		}
		
		Result deepest = res.get(0);
		boolean sameDepth = true;
		
		for (int i = 1; i < res.size(); i++) {
			Result now = res.get(i);
			if (now.depth != deepest.depth) {
				sameDepth = false;
				if (now.depth > deepest.depth) {
					deepest.depth = now.depth + 1;
					deepest.root = now.root;
				}
			}
		}
		
		if (sameDepth) {
			deepest.root = node;
			deepest.depth = deepest.depth + 1;
		} else {
//			deepest.root = 
		}
		
		return deepest;
	}
	
	class Result {
		int depth;
		Node root;
		
		Result(int depth, Node root) {
			this.depth = depth;
			this.root = root;
		}
		
		Result(){}
	}
}

class Node {
	int val;
	List<Node> children = null;
	
	Node(int val) {
		this.val = val;
	}
}