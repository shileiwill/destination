package cc150.chapter4.recursion.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;
import chapter4.linkedlist.ListNode;

public class ListOfDepths {

	public static void main(String[] args) {

	}

	List<LinkedList<TreeNode>> listOfDepthsDFS(TreeNode root) {
		List<LinkedList<TreeNode>> res = new ArrayList<LinkedList<TreeNode>>();
		dfs(root, res, 0);
		return res;
	}
	
	void dfs(TreeNode root, List<LinkedList<TreeNode>> res, int level) {
		if (root == null) {
			return;
		}
		LinkedList<TreeNode> list = null;
		if (res.size() == level) { // A new level
			list = new LinkedList<TreeNode>();
		} else {
			list = res.get(level);
		}
		
		list.add(root); // Pre-order, In-order, Post-order dont matter
		dfs(root.left, res, level + 1);
		dfs(root.right, res, level + 1);
	}
	
	List<ListNode> listOfDepths(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<ListNode> res = new ArrayList<ListNode>();
		
		queue.offer(root);
		res.add(new ListNode(root.val));
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			ListNode dummy = new ListNode(-1);
			ListNode cur = dummy;
			
			for (int i = 0; i < size; i++) {
				TreeNode treeNode = queue.poll();
				ListNode node = new ListNode(treeNode.val);
				cur.next = node;
				cur = cur.next;
				
				if (treeNode.left != null) {
					queue.offer(treeNode.left);
				}
				if (treeNode.right != null) {
					queue.offer(treeNode.right);
				}
			}
			
			res.add(dummy.next);
		}
		
		return res;
	}
}
