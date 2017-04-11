package company.yahoo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import chapter3.binaryTree.TreeNode;
import chapter4.linkedlist.ListNode;

public class MultiThread {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Thread1()); // Need to initialize in this way!
		t1.setName("Thread1Name");
		t1.start();
		
		Thread2 t2 = new Thread2();
		t2.start();
		
		MultiThread mt = new MultiThread();
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		n3.right = n6;
		
		List<Integer> res = mt.pathSum(n2);
		for (int val : res) {
			System.out.println(val);
		}
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
//		node5.next = node6;
		
//		ListNode node = mt.swapPair(node1);
//		while (node != null) {
//			System.out.print(node.val + "==");
//			node = node.next;
//		}
		
		ListNode node = mt.swapKGroup(node1, 3);
		while (node != null) {
			System.out.print(node.val + "==");
			node = node.next;
		}
	}
	
	ListNode swapPair(ListNode head) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		
		while (head != null && head.next != null) {
			ListNode first = head;
			ListNode second = head.next;
			ListNode next = head.next.next;
			
			prev.next = second;
			second.next = first;
			first.next = null;
			prev = first;
			
			head = next;
		}
		
		if (head != null) {
			prev.next = head;
		}
		
		return dummy.next;
	}
	
	ListNode swapKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		
		LinkedList<ListNode> stack = new LinkedList<ListNode>();
		while (head != null) {
			while (!stack.isEmpty()) {
				prev.next = stack.removeLast();
				prev = prev.next;
				prev.next = null;
			}
			
			for (int i = 0; i < k && head != null; i++) {
				stack.addLast(head);
				head = head.next;
			}
		}
		
		if (stack.size() == k) {
			while (!stack.isEmpty()) {
				prev.next = stack.removeLast();
				prev = prev.next;
				prev.next = null;
			}
		} else {
			// Dont reverse
			while (!stack.isEmpty()) {
				prev.next = stack.removeFirst();
				prev = prev.next;
				prev.next = null;
			}
		}
		
		return dummy.next;
	}
	
	List<Integer> pathSum(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		helper(root, res, 0);
		return res;
	}
	
	void helper(TreeNode root, List<Integer> res, int sum) {
		if (root == null) {
			return;
		}
		
		if (root.left == null && root.right == null) {
			sum += root.val;
			res.add(sum);
			return;
		}
		
		helper(root.left, res, sum + root.val);
		helper(root.right, res, sum + root.val);
	}
}
class Thread1 implements Runnable {
	@Override
	public void run() {
		System.out.println("In thread1 : " + Thread.currentThread().getName());
	}
}

class Thread2 extends Thread {
	public void run() {
		System.out.println("In thread2 : " + Thread.currentThread().getName());
	}
}