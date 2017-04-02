package company.yahoo;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class FloorOfNumInBST {

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
		
		FloorOfNumInBST f = new FloorOfNumInBST();
		double res = f.floor(n3, 1.5);
		System.out.println(res);
	}

	double floor(TreeNode node, double num) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = node;
		TreeNode prev = null;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode N = stack.pop();
			
			if (num == N.val) {
				return num;
			}
			
			if (prev != null && num > prev.val && num < N.val) {
				return prev.val;
			}
			
			prev = N;
			cur = N.right;
		}
		
		return prev.val;
	}
}
