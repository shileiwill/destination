package company.yahoo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

public class DeepestRightNode {

	public static void main(String[] args) {
		DeepestRightNode d = new DeepestRightNode();
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		d.threeSides(n2);
//		d.rightDeep(n2);
		
//		System.out.println(d.candidate.val);
	}

	TreeNode candidate = null;
	void rightDeep(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode now = queue.poll();
			
			if (now.left != null) {
				queue.offer(now.left);
			}
			
			if (now.right != null) {
				candidate = now.right;
				queue.offer(now.right);
			}
		}
	}
	
	void threeSidesWrong(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		List<TreeNode> left = new ArrayList<TreeNode>();
		List<TreeNode> right = new ArrayList<TreeNode>();
		List<TreeNode> bottom = new ArrayList<TreeNode>();
		
		left.add(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			bottom = new ArrayList<TreeNode>();
			
			for (int i = 0; i < size; i++) {
				TreeNode now = queue.poll();
				bottom.add(now); // Keep updating
				
				if (now.left != null) {
					queue.offer(now.left);
					
					if (i == 0) {
						left.add(now.left);
					}
				}
				
				if (now.right != null) {
					queue.offer(now.right);
					
					if (i == size - 1) {
						right.add(now.right);
					}
				}
			}
		}
		
		for (TreeNode node : left) {
			System.out.print(node.val + "--");
		}
		System.out.println();
		for (TreeNode node : bottom) {
			System.out.print(node.val + "--");
		}
		System.out.println();
		for (int i = right.size() - 1; i >= 0; i--) {
			System.out.print(right.get(i).val + "--");
		}
	}
	
	void threeSides(TreeNode root) {
		List<TreeNode> left = new ArrayList<TreeNode>();
		List<TreeNode> right = new ArrayList<TreeNode>();
		TreeNode node = root;

		while (node != null) {
			left.add(node);
			node = node.left;
		}
		
		node = root.right;
		while (node != null) {
			right.add(node);
			node = node.right;
		}
		
		for (TreeNode n : left) {
			System.out.print(n.val + "--");
		}
		System.out.println();
		for (TreeNode n : right) {
			System.out.print(n.val + "--");
		}
	}
}
