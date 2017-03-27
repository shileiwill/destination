package company.amazon.new_03_16;

import chapter4.linkedlist.ListNode;

public class FindNthElementFromEndInLinkedList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		FindNthElementFromEndInLinkedList find = new FindNthElementFromEndInLinkedList();
		ListNode res = find.removeKthFromEnd(n1, 1);
		
		while (res != null) {
			System.out.print(res.val + " -- ");
			res = res.next;
		}
	}
	
	ListNode removeKthFromEnd(ListNode head, int k) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		
		ListNode prev = find(dummy, k);
		prev.next = prev.next.next; // No need to check prev.next != null
		
		return dummy.next;
	}
	
	// Let's find the prev element
	ListNode find(ListNode head, int k) {
		ListNode slow = head;
		ListNode fast = head;
		
//		int i = 1;
//		while (fast != null && i <= k) {
//			fast = fast.next;
//			i++;
//		}
		for (int i = 1; i <= k; i++) {
			fast = fast.next;
		}
		
		if (fast == null) {
			return null;
		}
		
		while (fast != null && fast.next != null) { // Stop at the last element
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
	}
	
	// This will find exactly the element
	ListNode find2(ListNode head, int k) {
		ListNode slow = head;
		ListNode fast = head;
		
		int i = 1;
		while (i <= k) {
			fast = fast.next;
			i++;
		}
		
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
	}
}
