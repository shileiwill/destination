package company.facebook;
// 重要
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class ConvertBSTToLinkedList {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n3.left = n1;
		n1.right = n2;
		n3.right = n4;
//		n1.left = n5;
		n4.right = n5;
		
		ConvertBSTToLinkedList bst = new ConvertBSTToLinkedList();
//		System.out.println(bst.deepCopy(n3).val);
		DListNode d = bst.bstToLinkedListPreOrderTraversal(n3);
		
//		while (d != null) {
//			System.out.print(d.val + " -- ");
//			d = d.next;
//		}
		
		TreeNode root = bst.buildFromPreOrderTraversalMyStyle(d);
		System.out.println(root.val);
	}
	
	boolean isFullTree(TreeNode node) {
		if (node == null) {
			return true;
		}
		
		if (node.left == null && node.right == null) {
			return true;
		}
		
		if (node.left == null || node.right == null) {
			return false;
		}
		
		boolean left = isFullTree(node.left);
		if (!left) {
			return false;
		}
		
		boolean right = isFullTree(node.right);
		if (!right) {
			return false;
		}
		
		return true;
		
		// This is better(code is cleaner), because of lazy initialization
		// return isFullTree(node.left) && isFullTree(node.right);
	}
	
	// You can also use Map, BFS. Anyway, need to traverse through the tree
	TreeNode deepCopy(TreeNode node) { // Tree is easy, since there is no loop
		if (node == null) {
			return null;
		}
		
		TreeNode copy = new TreeNode(node.val);
		
		copy.left = deepCopy(node.left);
		copy.right = deepCopy(node.right);
		
		return copy;
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
		
		// If want a circular double linked list
		prev.next = dummy.next;
		dummy.prev = prev;
		
		return dummy.next;
	}
	
	DListNode bstToLinkedListPreOrderTraversal(TreeNode node) {
		DListNode dummy = new DListNode(-1);
		DListNode prev = dummy;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			TreeNode now = stack.pop();
			
			DListNode dNode = new DListNode(now.val);
			prev.next = dNode;
			dNode.prev = prev;
			prev = dNode;
			
			if (now.right != null) {
				stack.push(now.right);
			}
			
			if (now.left != null) {
				stack.push(now.left);
			}
		}
		
		return dummy.next;
	}
	
	// Not sure if there is any bug
	// Build a balanced binary search tree based on the above linked list
	// 如果原来的BST不是balanced 你想转成一个balanced BST, 你需要inorder traversal, 之后每次取中点
	// 如果你不care是否是balanced, 你只想还原原来的Binary Search Tree, 那么你preorder traversal, 之后用下边的方法
	TreeNode balancedBST(DListNode head) {
		if (head == null) {
			return null;
		}
		
		TreeNode node = new TreeNode(head.val);
		DListNode preLarger = findPreLarger(head);
		
		if (preLarger != null) {
			DListNode secondHead = preLarger.next;
			preLarger.next = null;
			node.right = balancedBST(secondHead);
		}
		
		node.left = balancedBST(head.next);
		
		return node;
	}

	private DListNode findPreLarger(DListNode head) {
		DListNode cur = head.next;
		DListNode prev = head;
		
		while (cur != null && cur.val <= head.val) {
			prev = cur;
			cur = cur.next;
		}
		
		return cur == null ? null : prev;
	}
	
	// This is good, use this one!
	TreeNode buildFromPreOrderTraversalMyStyle(DListNode head) {
		if (head == null) {
			return null;
		}
		
		if (head.next == null) {
			return new TreeNode(head.val);
		}
		
		TreeNode root = new TreeNode(head.val);
		DListNode preLarger = findPreLargerMyStyle(head);
		
		if (preLarger == null) { // 右边没有更大的 只有左节点 没有右孩子
			root.left = buildFromPreOrderTraversalMyStyle(head.next);
			return root;
		} else if (preLarger == head) { // 右边第一个就是更大的 只有右节点 没有左孩子
			root.right = buildFromPreOrderTraversalMyStyle(head.next);
			return root;
		} else {
			DListNode head1 = head.next;
			DListNode head2 = preLarger.next;
			preLarger.next = null;
			
			root.left = buildFromPreOrderTraversalMyStyle(head1);
			root.right = buildFromPreOrderTraversalMyStyle(head2);
			return root;
		}
	}

	private DListNode findPreLargerMyStyle(DListNode head) {
		int val = head.val;
		DListNode prev = head;
		DListNode cur = head.next;
		
		while (cur != null) {
			if (cur.val > val) {
				break;
			}
			
			prev = cur;
			cur = cur.next;
		}
		
		if (cur == null) {
			return null; // No larger found
		} else {
			return prev;
		}
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
