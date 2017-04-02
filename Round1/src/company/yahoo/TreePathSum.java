package company.yahoo;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

public class TreePathSum {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n5.right = n3;
		n5.left = n1;
		
		TreePathSum tps = new TreePathSum();
//		tps.pathSum(n2);
		
		List<Integer> res = new ArrayList<Integer>();
		tps.getAllPathSum(res, n2, 0);
//		System.out.println(tps.max);
		
		for (int val : res) {
			System.out.print(val + " : ");
		}
	}

	int max = Integer.MIN_VALUE;
	
	int pathSum(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = pathSum(node.left);
		int right = pathSum(node.right);
		
		int sum = Math.max(node.val, Math.max(left + node.val, right + node.val));
		max = Math.max(max, Math.max(sum, left + right + node.val));
		
		return sum;
	}
	
	void getAllPathSum(List<Integer> res, TreeNode node, int sum) {
		if (node.left == null && node.right == null) {
			sum += node.val;
			res.add(sum);
			return;
		}
		
		if (node.left != null) {
			getAllPathSum(res, node.left, sum + node.val);
		}
		
		if (node.right != null) {
			getAllPathSum(res, node.right, sum + node.val);
		}
	}
}
