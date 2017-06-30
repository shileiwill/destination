package company.facebook;

import chapter3.binaryTree.TreeNode;
/**
 * Geeks for Geeks. 
 * Given a tree of n even nodes. The task is to find the maximum number of edges to be removed from the given tree 
 * to obtain forest of trees having even number of nodes. This problem is always solvable as given graph has even nodes.
 */
public class EvenTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(100);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		
		node2.left = node5;
		node2.right = node6;
		node3.left = node7;
		node4.right = node8;
		node5.right = node9;
//		node6.right = node10;
		
		EvenTree et = new EvenTree();
		et.dfs(root);
	}

	int count = 0;
	int dfs(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = dfs(node.left);
		int right = dfs(node.right);
		int res = left + right + 1; // How many nodes under this root, including itself
		
		if (res % 2 == 0) {
			count++;
			System.out.println("You can cut this line, above node " + node.val);
		}
		
		return res;
	}
}
