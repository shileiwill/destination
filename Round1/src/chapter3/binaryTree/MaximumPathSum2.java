package chapter3.binaryTree;
/**
 * Given a binary tree, find the maximum path sum from root.
The path may end at any node in the tree and contain at least one node in it.
 */
public class MaximumPathSum2 {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node6 = new TreeNode(4);
		node1.left = node6;
		node1.right = node2;
		node2.left = node3;
		
		MaximumPathSum2 it = new MaximumPathSum2();
		int res = it.maxPathSum2(node1);
		System.out.println(res);
	}

	public int maxPathSum2(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return helper(root, 0);
	}
	
	int helper(TreeNode root, int sum) {
		if (root == null) {
			return sum;
		}
		
		int res = sum + root.val;
		
		int left = helper(root.left, res);
		int right = helper(root.right, res);
		
		return Math.max(left, right);
	}
}
