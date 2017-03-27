package company.amazon;

import chapter3.binaryTree.TreeNode;

public class HeightOfTree {

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
		n1.left = n6;
		n3.right = n4;
		
		System.out.println(getHeight(n5));
	}

	// Iteratively, we can use level order traversal to get the height
	
	// Recursion
	static int getHeight(TreeNode node) {
		if (node == null) {
			return -1;
		}
		
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
}
