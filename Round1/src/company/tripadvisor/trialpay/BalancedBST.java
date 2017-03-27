package company.tripadvisor.trialpay;

import chapter3.binaryTree.TreeNode;

public class BalancedBST {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n5.left = n1;
		
		BalancedBST b = new BalancedBST();
		ResultType rt = b.isBSTBalanced(n2);
		System.out.println(rt.isBalanced + ":" + rt.height);
	}

	ResultType isBSTBalanced(TreeNode root) {
		if (root == null) {
			return new ResultType(true, 0);
		}
		
		ResultType left = isBSTBalanced(root.left);
		ResultType right = isBSTBalanced(root.right);
		
		if (!left.isBalanced || !right.isBalanced) {
			return new ResultType(false, -1);
		}
		
		if (Math.abs(left.height - right.height) > 1) {
			return new ResultType(false, -1);
		}
		
		return new ResultType(true, Math.max(left.height, right.height) + 1);
	}
}

class ResultType {
	boolean isBalanced;
	int height;
	
	ResultType(boolean isBST, int height) {
		this.isBalanced = isBST;
		this.height = height;
	}
}