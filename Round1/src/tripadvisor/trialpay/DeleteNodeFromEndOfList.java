package tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.List;

import chapter4.linkedlist.ListNode;

public class DeleteNodeFromEndOfList {

	public static void main(String[] args) {
		DeleteNodeFromEndOfList deleteNode = new DeleteNodeFromEndOfList();
		
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		int len = deleteNode.getLength(n1);
		ListNode res = deleteNode.deleteFromEnd(n1, 1);
		while (res != null) {
			System.out.print(res.val + " == ");
			res = res.next;
		}
	}

	ListNode deleteFromEnd(ListNode head, int k) {
		ListNode kNode = goTo(head, k);
		
		if (kNode == null) { // Want to remove the first element
			return head.next;
		}
		
		ListNode node = head;
		while (kNode.next != null) {
			node = node.next;
			kNode = kNode.next;
		}
		
		node.next = node.next.next;
		return head;
	}
	
	ListNode goTo(ListNode head, int k) {
		int i = 1;
		while (i <= k) {
			head = head.next;
			i++;
		}
		
		return head;
	}
	
	ListNode deleteFromBeginning(ListNode head, int k) {
		// K is valid
		if (k <= 0) {
			return null;
		}
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		
		int i = 1;
		ListNode node = head;
		while (node != null && i < k) {
			prev.next = node;
			prev = prev.next;
			node = node.next;
			i++;
		}
		
		if (node == null) { // k is too large
			return dummy.next;
		}
		
		ListNode next = node.next;
		prev.next = next;
		
		return dummy.next;
	}
	
	ListNode deleteFromBeginningSpace(ListNode head, int k) {
		List<ListNode> list = new ArrayList<ListNode>();
		
		int i = 1;
		while (head != null) {
			if (i != k) {
				list.add(head);
			}
			i++;
			head = head.next;
		}
		
		for (i = 0; i < list.size() - 1; i++) {
			ListNode now = list.get(i);
			ListNode next = list.get(i + 1);
			now.next = next;
			next.next = null;
		}
		
		return list.size() == 0 ? null : list.get(0);
	}
	
	int getLength(ListNode head) {
		int len = 0;
		
		while (head != null) {
			len++;
			head = head.next;
		}
		
		return len;
	}
}
