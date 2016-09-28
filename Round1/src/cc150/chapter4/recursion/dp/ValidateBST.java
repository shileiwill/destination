package cc150.chapter4.recursion.dp;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class ValidateBST {

	public static void main(String[] args) {

	}

	// This will not work, we should not consider only the immediate parent and children
	boolean validateBST0(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		if (root.left != null && root.right != null) {
			if (root.left.val > root.val || root.right.val < root.val) {
				return false;
			}
			return validateBST0(root.left) && validateBST0(root.right);
		} else if (root.left != null) {
			if (root.left.val > root.val) {
				return false;
			}
			return validateBST0(root.left);
		} else {
			if (root.right.val < root.val) {
				return false;
			}
			return validateBST0(root.right);
		}
	}
	
	boolean validateBSTInOrderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		int prev = Integer.MIN_VALUE;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			
			if (node.val <= prev) {
				return false;
			}
			
			cur = node.right;
			prev = node.val;
		}
		
		return true;
	}
	
	boolean validateBST(TreeNode root) {
		ResultType rt = dfs(root);
		return rt.isBST;
	}
	
	ResultType dfs(TreeNode root) {
		if (root == null) {
			return new ResultType(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
		}
		
		ResultType left = dfs(root.left);
		if (!left.isBST) {
			return new ResultType(-1, -1, false);
		}
		
		ResultType right = dfs(root.right);
		if (!right.isBST) {
			return new ResultType(-1, -1, false);
		}
		
		if (root.val < left.max || root.val > right.min) {
			return new ResultType(-1, -1, false);
		}
		
		return new ResultType(Math.min(root.val, left.min), Math.max(root.val, right.max), true);
	}
	
	class ResultType {
		int min;
		int max;
		boolean isBST;
		
		ResultType(int min, int max, boolean isBST) {
			this.min = min;
			this.max = max;
			this.isBST = isBST;
		}
	}
}

