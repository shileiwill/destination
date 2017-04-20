package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		TreeNode n31 = new TreeNode(31);
		TreeNode n11 = new TreeNode(11);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n5.left = n1;
		
		n1.left = n31;
		n3.right = n11;
		
		// Level Order using alternative flag will be 2, 5, 4, 3, 1
		List<Integer> res = levelOrderTraversal.levelOrderTraversal(n2);
		for (int val : res) {
			System.out.print(val + "--");
		}
	}

	List<Integer> levelOrderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		
		if (root == null) {
			return res;
		}
		
		boolean rightToLeft = true;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			// Use LinkedList to easily addFirst and addLast
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				
				// add to first or last based on the order
				if (rightToLeft) {
					list.addFirst(node.val);
				} else {
					list.addLast(node.val);
				}
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			
			// Change the insert order every level
			rightToLeft = !rightToLeft;
			res.addAll(list);
		}
		
		return res;
	}
}
