package company.linkedin;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class ConvertBST2DoublyLinkedList {

	public static void main(String[] args) {

	}

	static class Node {
		Node prev, next;
		int val;
		
		Node(int val) {
			this.val = val;
		}
	}
	
	Node convert(TreeNode root) {
		Node dummy = new Node(-1);
		Node prev = dummy;
		
		// In-order traversal the tree, and build Linked List in the meantime
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curTree = root;
		
		while (curTree != null || !stack.isEmpty()) {
			while (curTree != null) {
				stack.push(curTree);
				curTree = curTree.left;
			}
			
			TreeNode nowTree = stack.pop();
			
			Node node = new Node(nowTree.val);
			
			prev.next = node;
			node.prev = prev;
			prev = node;
			
			curTree = nowTree.right;
		}
		
		return dummy.next;
	}
	
	/**
	 * 把TreeNode转换成circular linkedlist in place (使用treenode结构). 用了个stack in place traversal, 习惯性的用了个虚拟点做头，最后面试官不爽要求我去掉。. 
	 */
	Node convert2(TreeNode root) {
		Node head = null;
		Node cur = null;
		
		// In-order traversal the tree, and build Linked List in the meantime
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curTree = root;
		
		while (curTree != null || !stack.isEmpty()) {
			while (curTree != null) {
				stack.push(curTree);
				curTree = curTree.left;
			}
			
			TreeNode nowTree = stack.pop();
			
			Node node = new Node(nowTree.val);
			
			if (cur == null) {
				head = node;
				cur = node;
			} else {
				cur.next = node;
				node.prev = cur;
				cur = node;
			}
			
			curTree = nowTree.right;
		}
		
		cur.next = head; // Make it a circle
		head.prev = cur;
		
		return head;
	}
}
