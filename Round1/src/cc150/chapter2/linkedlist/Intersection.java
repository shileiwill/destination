package cc150.chapter2.linkedlist;

import java.util.ArrayList;
import java.util.List;

import chapter4.linkedlist.ListNode;

public class Intersection {

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
		
//		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node3;
		
		Intersection rd = new Intersection();
		boolean res = rd.isIntersected2(node1, node5);
		
		System.out.print(res);
	}

	boolean isIntersected2(ListNode left, ListNode right) {
		int leftLen = getLength(left);
		int rightLen = getLength(right);
		int diff = Math.abs(leftLen - rightLen);
		
		ListNode longer = (leftLen >= rightLen) ? left : right;
		ListNode shorter = (leftLen < rightLen) ? left : right;
		
		for (int i = 0; i < diff; i++) {
			longer = longer.next;
		}
		
		while (longer != null) {
			if (longer == shorter) {
				return true;
			}
			longer = longer.next;
			shorter = shorter.next;
		}
		
		return false;
	}
	
	int getLength(ListNode head) {
		ListNode fast = head;
		int len = 1;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			len = len + 2;
		}
		
		if (fast == null) {
			len -= 1;
		}
		
		return len;
	}
	
	boolean isIntersected(ListNode left, ListNode right) {
		List<ListNode> exist = new ArrayList<ListNode>();
		while (left != null) {
			exist.add(left);
			left = left.next;
		}
		
		while (right != null) {
			if (exist.contains(right)) {
				return true;
			}
			right = right.next;
		}
		return false;
	}
}
