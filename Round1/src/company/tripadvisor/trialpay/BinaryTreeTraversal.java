package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class BinaryTreeTraversal {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		BinaryTreeTraversal l = new BinaryTreeTraversal();
		List<TreeNode> res = l.postOrder(n2);
		
		for (TreeNode node : res) {
			System.out.print(node.val + "==");
		}
		
		List<Integer> inorder = new ArrayList<Integer>();
		inorder.add(5);
		inorder.add(2);
		inorder.add(4);
		inorder.add(1);
		inorder.add(3);
		
		List<Integer> preorder = new ArrayList<Integer>();
		preorder.add(2);
		preorder.add(5);
		preorder.add(4);
		preorder.add(3);
		preorder.add(1);
		
		TreeNode root = l.build(inorder, preorder);
		System.out.println(root.val);
	}

	List<TreeNode> postOrder(TreeNode root) {
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				list.addFirst(cur);
				cur = cur.right;
			} else {
				TreeNode node = stack.pop(); // Everything in stack is already added to list
				cur = node.left;
			}
		}
		
		return list;
	}
	
	// Given preorder and inorder, find the original tree
	TreeNode build(List<Integer> inorder, List<Integer> preorder) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // Node.val, index in inorder
		for (int i = 0; i < inorder.size(); i++) {
			map.put(inorder.get(i), i);
		}
		
		TreeNode root = helper(preorder, map, 0, inorder.size() - 1, 0, preorder.size() - 1);
		return root;
	}
	
	TreeNode helper(List<Integer> preorder, Map<Integer, Integer> map, int leftIn, int rightIn, int leftPre, int rightPre) {
		if (leftIn > rightIn || leftPre > rightPre) {
			return null;
		}
		TreeNode root = new TreeNode(preorder.get(leftPre));
		
		int inOrderPos = map.get(root.val);
		int length = inOrderPos - leftIn;
		root.left = helper(preorder, map, leftIn, inOrderPos - 1, leftPre + 1, leftPre + length);
		root.right = helper(preorder, map, inOrderPos + 1, rightIn,  leftPre + length + 1, rightPre);
		
		return root;
	}
}
