package company.expedia;

import chapter3.binaryTree.TreeNode;

public class IfInBST {

	public static void main(String[] args) {
		TreeNode n5 = new TreeNode(5);
		TreeNode n3 = new TreeNode(3);
		TreeNode n8 = new TreeNode(8);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		TreeNode n4 = new TreeNode(4);
		
		n5.left = n3;
		n5.right = n8;
		n3.left = n1;
//		n1.left = n6;
		n3.right = n4;
		
		IfInBST inBST = new IfInBST();
		System.out.println(inBST.inBST(0, n5));
	}

	int inBST(int target, TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		if (root.val == target) {
			return 1;
		} else if (root.val < target) {
			return inBST(target, root.right);
		} else {
			return inBST(target, root.left);
		}
	}
}
