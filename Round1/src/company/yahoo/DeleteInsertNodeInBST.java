package company.yahoo;

import chapter3.binaryTree.TreeNode;

public class DeleteInsertNodeInBST {

	TreeNode insertNodeIterative(TreeNode root, TreeNode node) {
		if (root == null) {
			root = node;
			return root;
		}
		
		TreeNode prev = null;
		TreeNode cur = root;
		
		while (cur != null) {
			prev = cur;
			if (node.val < cur.val) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		
		if (prev.val < node.val) {
			prev.right = node;
		} else {
			prev.left = node;
		}
		
		return root;
	}
	
	TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {  // Important!
			return node;
		}
		
		if (node.val < root.val) {
			root.left = insertNode(root.left, node);
		} else {
			root.right = insertNode(root.right, node);
		}
		
		return root;
	}
	
	TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		
		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else { // Find that node
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}
			
			TreeNode minNode = findMin(root);
			root.val = minNode.val;
			root.right = deleteNode(root.right, minNode.val);
		}
		
		return root;
	}
	
	TreeNode findMin(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		
		return node;
	}
}
