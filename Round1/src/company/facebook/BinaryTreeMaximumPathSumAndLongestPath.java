package company.facebook;

import chapter3.binaryTree.TreeNode;

public class BinaryTreeMaximumPathSumAndLongestPath {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
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
//		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		
//		node2.left = node5;
//		node2.right = node6;
		node3.left = node7;
		node4.right = node8;
		node8.left = node9;
		node8.right = node10;
		
		BinaryTreeMaximumPathSumAndLongestPath bt = new BinaryTreeMaximumPathSumAndLongestPath();
		int res = bt.longestPath(root);
		System.out.println(res);
	}

	/*
	 * 给定一个二叉树，任意两个节点之间必然是有一条路径相通的，假定父节点和它的孩子节点的距离为单位1，求二叉树中相距最远的两个节点间的路径长度
	 * 二叉树最长路径 不必经过root， 这不就是Diameter吗
	 * Leaf to Leaf longest path
	 */
	int max = 0;
	public int longestPath(TreeNode root) {
		helper(root);
		return max;
	}
	
	int helper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = helper(node.left);
		int right = helper(node.right);
		
		int curHeight = Math.max(left, right) + 1;
		int curLength = left + right + 1;
		max = Math.max(curLength, max);
		
		return curHeight;
	}
	
	/**
	 * 124. Binary Tree Maximum Path Sum
	 * Given a binary tree, find the maximum path sum.

	For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree 
	along the parent-child（来回都可以） connections. 
	The path must contain at least one node and does not need to go through the root.
	
	For example:
	Given the below binary tree,
	
	       1
	      / \
	     2   3
	Return 6.
	 */
	// singleSum: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
    // maxSum: 从树中任意到任意点的最大路径，这条路径至少包含一个点
    class ResultType {
        int singleSum;
        int maxSum;
        
        ResultType(int singleSum, int maxSum) {
            this.singleSum = singleSum;
            this.maxSum = maxSum;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType rt = helper2(root);
        
        return rt.maxSum;
    }
    
    ResultType helper2(TreeNode node) {
        if (node == null) {
            return new ResultType(0, Integer.MIN_VALUE); // 这个MIN很重要
        }
        
        // Divide 
        ResultType left = helper2(node.left);
        ResultType right = helper2(node.right);
        
        // Conquer
        int singleSum = Math.max(left.singleSum, right.singleSum) + node.val;
        singleSum = Math.max(0, singleSum);
        
        int maxSum = Math.max(left.maxSum, right.maxSum);
        maxSum = Math.max(maxSum, left.singleSum + right.singleSum + node.val);
        
        return new ResultType(singleSum, maxSum);
    }
}
