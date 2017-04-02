package company.yahoo;

import chapter4.linkedlist.ListNode;

public class FindCycleInLinkedList {

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
		
//		node6.next = node3;
		
		FindCycleInLinkedList cycle = new FindCycleInLinkedList();
		boolean hasCycle = cycle.hasCycle(node1);
		System.out.println(hasCycle);
	}

	boolean hasCycle(ListNode node) {
		ListNode slow = node;
		ListNode fast = node.next;
		
		while (fast != null && fast.next != null) {
			if (slow == fast) {
				return true;
			}
			
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return false;
	}
}
