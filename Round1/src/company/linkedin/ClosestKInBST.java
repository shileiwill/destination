package company.linkedin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * Given a BST, find the closest k node in the tree. closest means node value
 */
public class ClosestKInBST {

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
		
		ClosestKInBST ck = new ClosestKInBST();
		ck.closestK(n2, 4, 2);
	}

	// Do a In Order Traversal, Use Binary Search find closest one, then go left, right to find K
	List<TreeNode> closestK(TreeNode root, int target, int k) {
		PriorityQueue<TreeNode> heap = new PriorityQueue<TreeNode>(k + 1, new Comparator<TreeNode>(){
			public int compare(TreeNode node1, TreeNode node2) {
				return getDistance(node2.val, target) - getDistance(node1.val, target);
			}
		});
		
		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode node = stack.pop();
			heap.offer(node);
			
			if (heap.size() > k) {
				heap.poll();
			}
			
			cur = node.right;
		}
		
		List<TreeNode> res = new ArrayList<TreeNode>();
		while (!heap.isEmpty()) {
			TreeNode node = heap.poll();
			res.add(node);
			System.out.println(node.val);
		}
		
		return res;
	}
	
	int getDistance(int val, int target) {
		return Math.abs(val - target);
	}
}
