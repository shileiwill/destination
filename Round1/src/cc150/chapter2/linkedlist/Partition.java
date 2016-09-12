package cc150.chapter2.linkedlist;

import chapter4.linkedlist.ListNode;

public class Partition {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(7);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(9);
		ListNode node5 = new ListNode(1);
		ListNode node6 = new ListNode(2);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		
		Partition rd = new Partition();
		ListNode res = rd.partition(node1, 10);
		
		while (res != null) {
			System.out.print(res.val + " -- ");
			res = res.next;
		}
	}

	ListNode partition(ListNode head, int k) {
		ListNode dummyLeft = new ListNode(-1);
		ListNode left = dummyLeft;
		ListNode dummyRight = new ListNode(-1);
		ListNode right = dummyRight;
		
		while (head != null) {
			if (head.val < k) {
				left.next = head;
				left = left.next;
			} else {
				right.next = head;
				right = right.next;
			}
			head = head.next;
		}
		
		right.next = null;
		left.next = dummyRight.next;
		
		return dummyLeft.next;
	}
}
