package company.tripadvisor.trialpay;

import chapter3.binaryTree.TreeNode;

public class MaxPathSumInTree {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(-3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		MaxPathSumInTree mp = new MaxPathSumInTree();
//		mp.maxPathSum(n2);
		mp.amplitude(n2);
		
		System.out.println(mp.max);
	}

	int max = Integer.MIN_VALUE;
	
	int maxPathSum(TreeNode node) { // MUST Connect with current node
		if (node == null) {
			return 0;
		}
		
		int left = maxPathSum(node.left);
		int right = maxPathSum(node.right);
		
		int curMax = Math.max(node.val, Math.max(node.val + left, node.val + right)); // Can choose only 1 path
		
		max = Math.max(max, Math.max(curMax, node.val + left + right)); // Can be a curve
		
		return curMax;
	}
	
	/** 振幅
	 * In a binary tree T, a path P is a non-empty sequence of nodes of tree such that, each consecutive node in the sequence is a subtree of its 
	 * preceding node. In the example tree, the sequences [9, 8, 2] and [5, 8, 12] are two paths, while [12, 8, 2] is not. 
	 * The amplitude of path P is the maximum difference among values of nodes on path P. The amplitude of tree T is the maximum amplitude of all paths in T. 
	 * When the tree is empty, it contains no path, and its amplitude is treated as 0.
		For exmaple.
		
		
		Input:
		         5
		       /   \
		    8        9
		   /  \     /  \ 
		12   2  8   4
		          /    /
		        2    5
		Output:
		7
		Explanation:
		The paths [5, 8, 12] and [9, 8, 2] have the maximum amplitude 7.
		
		Naive Thinking: 关键句是The amplitude of path P is the maximum difference among values of nodes on path P. 可以采用DFS，记录一条路径上的最大值和最小值，求差值，最后再总的求一个最大值。
		算法复杂度是 O(n), space O(n)。 
	 */
	
	// Path is from root to leaf
	int amplitude = Integer.MIN_VALUE;
	int amplitude(TreeNode node) {
		int[] minMax = {node.val, node.val};
		dfs(node, minMax);
		System.out.println("Max Amplitude : " + amplitude);
		return amplitude;
	}
	
	void dfs(TreeNode node, int[] minMax) {
		if (node.left == null && node.right == null) {
			amplitude = Math.max(amplitude, minMax[1] - minMax[0]);
			return;
		}
		
		if (node.left != null) {
			int[] newMinMax = {minMax[0], minMax[1]}; // Need to have a new array for next recursion
			if (node.left.val < newMinMax[0]) {
				newMinMax[0] = node.left.val;
			}
			if (node.left.val > newMinMax[1]) {
				newMinMax[1] = node.left.val;
			}
			
			dfs(node.left, newMinMax);
		}
		
		if (node.right != null) {
			int[] newMinMax = {minMax[0], minMax[1]}; // Need to have a new array for next recursion
			if (node.right.val < newMinMax[0]) {
				newMinMax[0] = node.right.val;
			}
			if (node.right.val > newMinMax[1]) {
				newMinMax[1] = node.right.val;
			}
			
			dfs(node.right, newMinMax);
		}
	}
}
