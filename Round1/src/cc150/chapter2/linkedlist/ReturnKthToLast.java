package cc150.chapter2.linkedlist;

import chapter4.linkedlist.ListNode;

public class ReturnKthToLast {

	public static void main(String[] args) {
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
		node5.next = node6;
		
		ReturnKthToLast rd = new ReturnKthToLast();
		int node = rd.kthToLast(node1, 5);
		System.out.println(node);
	}

	int kthToLast(ListNode head, int k) {
		ListNode slow = head;
		ListNode fast = head;
		
		for (int i = 1; i <= k; i++) {
			if (fast == null) {
				return -1;
			}
			fast = fast.next;
		}
		
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow.val;
	}
	
	// Recursive approach
	int count = 0; // This can be in a wrapper class or int[]
	ListNode kthToLast2(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode node = kthToLast2(head.next, k);
		count++; // Count from the end
		if (count == k) {
			return head;
		}
		return node;
	}
}
