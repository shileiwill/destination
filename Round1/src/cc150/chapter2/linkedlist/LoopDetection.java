package cc150.chapter2.linkedlist;

import chapter4.linkedlist.ListNode;

public class LoopDetection {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(3);
		ListNode node6 = new ListNode(2);
		ListNode node7 = new ListNode(11);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		node4.next = node5;
		node5.next = node6;
		node6.next = node3;
//		node7.next = node3;
		
		LoopDetection rd = new LoopDetection();
		ListNode res = rd.loopDetection(node1);
		
		System.out.print(res.val);
	}
	
	// It is easy to find the duplicate element by using Hash
	ListNode loopDetection(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				break;
			}
		}
		
		// No loop at all
		if (fast == null || fast.next == null) {
			return null;
		}
		
		fast = head;
		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
	}
}
