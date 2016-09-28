package cc150.chapter4.recursion.dp;

import chapter3.binaryTree.TreeNode;

public class CheckBalanced {

	public static void main(String[] args) {

	}

	boolean checkBalanced(TreeNode root) {
		ResultType rt = dfs(root);
		return rt.isBalanced;
	}
	
	ResultType dfs(TreeNode root) {
		if (root == null) {
			return new ResultType(0, true);
		}
		
		ResultType leftRT = dfs(root.left);
		if (!leftRT.isBalanced) {
			return new ResultType(Integer.MIN_VALUE, false);
		}
		
		ResultType rightRT = dfs(root.right);
		if (!rightRT.isBalanced) {
			return new ResultType(Integer.MIN_VALUE, false);
		}
		
		if (Math.abs(leftRT.height - rightRT.height) > 1) {
			return new ResultType(Integer.MIN_VALUE, false);
		}
		
		return new ResultType(Math.max(leftRT.height, rightRT.height) + 1, true);
	}
}
class ResultType {
	int height;
	boolean isBalanced;
	
	ResultType(int height, boolean isBalanced) {
		this.height = height;
		this.isBalanced = isBalanced;
	}
}