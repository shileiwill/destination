package company.yahoo;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class BSTToLinkedList {

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
		
		BSTToLinkedList bst = new BSTToLinkedList();
		DListNode d = bst.bstToLinkedList(n3);
		
		while (d != null) {
			System.out.print(d.val + " -- ");
			d = d.next;
		}
	}

	DListNode bstToLinkedList(TreeNode node) {
		DListNode dummy = new DListNode(-1);
		DListNode prev = dummy;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = node;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode now = stack.pop();
			
			DListNode dNode = new DListNode(now.val);
			prev.next = dNode;
			dNode.prev = prev;
			prev = dNode;
			
			cur = now.right;
		}
		return dummy.next;
	}
}

class DListNode {
	DListNode next;
	DListNode prev;
	int val;
	
	DListNode(int val) {
		this.val = val;
	}
}
