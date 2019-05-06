package company.snapchat;
//重要
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class SerializeDeserializeTree {
	// Nary tree
	String serialize(Node root) {
		if (root == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("(");
		sb.append(root.val);
		for (Node child : root.children) { // 有孩子就往里加，没有就(5)
			sb.append(serialize(child));
		}
		sb.append(")");
		
		return sb.toString();
	}
	
	Node deserialize(String s) {
		Node root = null;
		Node parent = null;
		Stack<Node> stack = new Stack<Node>();
		int pos = 0;
		
		while (pos < s.length()) {
			char c = s.charAt(pos);
			
			if (c == '(') {
				pos++; // Next must be a number
				
				Node node = new Node(s.charAt(pos) - '0');
				if (root == null) {
					root = node; // Will come here only once
				}
				if (parent != null) {
					parent.children.add(node);
				}
				
				parent = node;
				stack.push(node); // 记录一下
			} else if (c == ')') {
				stack.pop(); // 弹出来
				
				if (!stack.isEmpty()) {
					parent = stack.peek(); // Change parent node, 1 level above
				}
			}
			
			pos++;
		}
		
		return root;
	}
	
	static class Node {
		int val;
		List<Node> children;
		
		Node(int val) {
			this.val = val;
			this.children = new ArrayList<Node>();
		}
	}
	
	/**
	 * If this is only a binary tree
	 */
	String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			
			if (node == null) {
				sb.append("#,"); // Make it a full tree
			} else {
				sb.append(node.val + ",");
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	TreeNode deserializeBinaryTree(String s) {
		String[] arr = s.split(",");
		int[] pos = {0};
		return helper(arr, pos);
	}
	
	TreeNode helper(String[] arr, int[] pos) {
		if (arr[pos[0]].equals("#")) {
			return null;
		}
		
		TreeNode root = new TreeNode(Integer.valueOf(arr[pos[0]]));
		
		pos[0] = pos[0] + 1;
		root.left = helper(arr, pos);
		
		pos[0] = pos[0] + 1;
		root.right = helper(arr, pos);
		
		return root;
	}
	
	// Given a String, construct binary tree. Is this good? No
	TreeNode constructBinaryTree(String s) {
		if (s.length() == 0) {
			return null;
		}
		
		int left = 0;
		int right = 0;
		
		while (right < s.length() && Character.isDigit(s.charAt(right))) {
			right++; // Eventually, right will be (
		}
		
		TreeNode root = new TreeNode(Integer.valueOf(s.substring(left, right)));
		
		if (right < s.length()) {
			left = right; // left is ( as well
			int count = 1; // Number of left bracket
			
			while (right + 1 < s.length() && count != 0) {
				right++;
				char c = s.charAt(right);
				
				if (c == '(') {
					count++;
				} else if (c == ')') {
					count--;
				}
			} // Eventually, right will be )
			
			root.left = constructBinaryTree(s.substring(left + 1, right));
		}
		
		right++;
		if (right < s.length()) {
			root.right = constructBinaryTree(s.substring(right + 1, s.length() - 1));
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		
		node1.children.add(node2);
		node1.children.add(node3);
		node1.children.add(node4);
		
		node3.children.add(node5);
		node3.children.add(node6);
		
		node4.children.add(node7);
		
		SerializeDeserializeTree sdt = new SerializeDeserializeTree();
		String res = sdt.serialize(node1); // (1(2)(3(5)(6))(4(7)))
		System.out.println(res);
		Node root = sdt.deserialize(res);
		System.out.println(root);
	}
}