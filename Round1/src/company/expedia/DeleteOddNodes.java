package company.expedia;

import chapter4.linkedlist.ListNode;

public class DeleteOddNodes {

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
		
		DeleteOddNodes don = new DeleteOddNodes();
		ListNode res = don.deleteOddNodes(n1);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

	ListNode deleteOddNodes(ListNode node) {
		
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		
		int i = 1;
		while (node != null) {
			if (i % 2 == 0) {
				cur.next = node;
				cur = cur.next;
			}
			node = node.next;
			i++;
		}
		
		return dummy.next;
	}
	
}
