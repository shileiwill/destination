package company.yahoo;

import chapter3.binaryTree.TreeNode;
/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two leaves in the tree. The diagram below shows two trees each with diameter nine, the leaves that form the ends of a longest path are shaded (note that there is more than one path in each tree of length nine, but no path longer than nine nodes).

The diameter of a tree T is the largest of the following quantities:

* the diameter of T’s left subtree
* the diameter of T’s right subtree
* the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
 */
public class HeightOfBinaryTree {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(-3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
//		n3.left = n1;
		
		HeightOfBinaryTree h = new HeightOfBinaryTree();
		System.out.println(h.getDiameterAndHeight(n2, new int[]{0}));
	}

	int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}

		// This guy is not required
		if (node.left == null && node.right == null) {
			return 1;
		}
		
		int left = getHeight(node.left);
		int right = getHeight(node.right);
		
		return Math.max(left, right) + 1;
	}
	
	int getDiameter(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		
		int leftDiameter = getDiameter(node.left);
		int rightDiameter = getDiameter(node.right);
		
		return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
	}
	
	int getDiameterAndHeight(TreeNode node, int[] height) {
		if (node == null) {
			height[0] = 0;
			return 0;
		}
		
		// As long as current node is not null, it's height is at least itself, which is 1
		int[] leftHeight = {1};
		int[] rightHeight = {1};
		
//		leftHeight[0] = 1; 
//		rightHeight[0] = 1;
		
		int leftDiameter = getDiameterAndHeight(node.left, leftHeight);
		int rightDiameter = getDiameterAndHeight(node.right, rightHeight);
		
		height[0] = Math.max(leftHeight[0], rightHeight[0]) + 1; // From bottom to top, build the height
		
		return Math.max(leftHeight[0] + rightHeight[0] + 1, Math.max(leftDiameter, rightDiameter));
	}
}
