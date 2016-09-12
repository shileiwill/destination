package cc150.chapter2.linkedlist;

import chapter4.linkedlist.ListNode;

public class DeleteMidNode {

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
		
		DeleteMidNode rd = new DeleteMidNode();
		rd.deleteMidNode(node3);
	}

	void deleteMidNode(ListNode midNode) {
		ListNode head = midNode; // Just for iteration later
		
		ListNode prev = midNode;
		while (midNode != null) {
			if (midNode.next == null) { // Last, terminate
				prev.next = null;
			}
			
			swapValue(prev, midNode);
			prev = midNode;
			midNode = midNode.next;
		}
		
		while (head != null) {
			System.out.print(head.val + " -- ");
			head = head.next;
		}
	}
	
	void swapValue(ListNode prev, ListNode node) {
		int temp = node.val;
		node.val = prev.val;
		prev.val = temp;
	}
}
