package company.linkedin;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

public class PathSum {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		PathSum ps = new PathSum();
		int res = ps.pathSum2(n2);
		System.out.println(res);
	}

	int sum = 0;
	int pathSum2(TreeNode root) {
		int[] cur = {root.val}; // 必须得用数组
		helper(root, cur);
		return sum;
	}
	
	void helper(TreeNode node, int[] cur) {
		if (node.left == null && node.right == null) {
			sum += cur[0];
			return;
		}
		
		if (node.left != null) {
			cur[0] = cur[0] * 10 + node.left.val;
			helper(node.left, cur);
			cur[0] = (cur[0] - node.left.val) / 10;
		}
		
		if (node.right != null) {
			cur[0] = cur[0] * 10 + node.right.val;
			helper(node.right, cur);
			cur[0] = (cur[0] - node.right.val) / 10;
		}
	}
	
	int pathSum(TreeNode root) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(root);
		helper(root, list);
		return sum;
	}
	
	void helper(TreeNode node, List<TreeNode> list) {
		if (node == null) {
			return;
		}
		
		if (node.left == null && node.right == null) {
			int cur = 0;
			for (TreeNode n : list) {
				cur = cur * 10 + n.val;
			}
			sum += cur;
			return;
		}
		
		if (node.left != null) {
			list.add(node.left);
			helper(node.left, list);
			list.remove(list.size() - 1);
		}
		
		if (node.right != null) {
			list.add(node.right);
			helper(node.right, list);
			list.remove(list.size() - 1);
		}
	}
}
