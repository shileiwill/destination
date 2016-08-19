package chapter4.linkedlist;
// Must know in Linked List
public class README {
//	1. Insert a Node in Sorted List
//	2. Remove a Node from Linked List
//	3. Reverse a Linked List
//	4. Merge Two Linked Lists
//	5. Find the Middle of a Linked List
//	6. Make sure set null at the end of list. If not, will cycle
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		node1.next = node2;
		node2.next = node3;
		ListNode head = node1;
		
		print(head);
		node1 = node2;
		print(head);
	}
	
	static void print(ListNode head) {
		for (ListNode node = head; node != null; node = node.next) {
			System.out.print(node.val);
			System.out.print("->");
		}
		System.out.println("null");
	}
}
