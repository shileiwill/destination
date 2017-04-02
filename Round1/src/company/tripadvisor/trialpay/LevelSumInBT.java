package company.tripadvisor.trialpay;

import java.util.LinkedList;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

public class LevelSumInBT {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n5.left = n1;
		n5.right = n6;
		
		LevelSumInBT ls = new LevelSumInBT();
		int res = ls.getLevelSum(n2, 2);
		System.out.println(res);
		System.out.println(ls.breadth);
	}

	int breadth = 0;
	int getLevelSum(TreeNode root, int k) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			breadth = Math.max(breadth, size);
			
			int sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode now = queue.poll();
				sum += now.val;
				
				if (now.left != null) {
					queue.offer(now.left);
				}
				if (now.right != null) {
					queue.offer(now.right);
				}
			}
			
			if (level == k) {
				return sum;
			}
			level++;
		}
		
		return -1;
	}
}
