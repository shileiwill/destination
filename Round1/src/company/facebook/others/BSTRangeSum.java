package company.facebook.others;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * given BST and two vals(lo an hi), return sum of nodes whose val in [lo , hi]。 O(N) and O(logN) solution
 * 这个需要在每个Node里加一个sum的field， 然后建树时储存每一个subtree的sum值在那个Node， 然后根据BST性质找到第一个落在[lo, hi]的Node， 得到SUM， 
 * 然后搜第一个比lo小的Node得到left SUM， 在搜第一个比hi大的Node得到right SUM， 然后用总sum减一下就好了
 * 
 */
public class BSTRangeSum {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n3.left = n1;
		n1.right = n2;
		n3.right = n4;
		n4.right = n5;
		
		BSTRangeSum bst = new BSTRangeSum();
//		int res = bst.getRangeSum(n3, 1, 1);
		
		bst.preprocessTree(n3);
		int res = bst.getRangeSum2(n3, 1, 1);
		System.out.println(res);
	}

	int getRangeSum(TreeNode root, int low, int high) {
		int sum = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		// Do an inorder traversal, sum all nodes between low and high, stop after high
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode now = stack.pop();
			if (now.val >= low && now.val <= high) {
				sum += now.val;
			}
			
			if (now.val > high) { // Yes, use this to stop.
				break;
			}
			
			cur = now.right;
		}
		
		return sum;
	}
	
	// Add sum to tree
	void preprocessTree(TreeNode root) {
		int sum = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		// Do an inorder traversal, Add sum field in Node
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode now = stack.pop();
			sum += now.val;
			now.sum = sum; // Add sum field in Node
			
			cur = now.right;
		}
	}
	
	int getRangeSum2(TreeNode root, int low, int high) {
		int small = helperSmall(root, low, false);
		int big = helperSmall(root, high, true);
		return big - small;
	}
	
	// O(LogN) Find the node which is slightly smaller than target
	int helperSmall(TreeNode root, int target, boolean inclusive) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		int prev = 0;
		
		// Do an inorder traversal, Add sum field in Node
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode now = stack.pop();
			
			if (inclusive && now.val > target) {
				return prev;
			}
			
			if (!inclusive && now.val >= target) {
				return prev;
			}
			
			prev = now.sum;
			cur = now.right;
		}
		
		return prev;
	}
}
