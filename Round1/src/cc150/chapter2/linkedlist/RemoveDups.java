package cc150.chapter2.linkedlist;

import java.util.ArrayList;
import java.util.List;

import chapter4.linkedlist.ListNode;

public class RemoveDups {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(3);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(1);
		ListNode node6 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		
		RemoveDups rd = new RemoveDups();
		ListNode head = rd.removeDups2(node1);
		
		while (head != null) {
			System.out.print(head.val + " -- ");
			head = head.next;
		}
	}

	ListNode removeDups(ListNode head) {
		List<Integer> exist = new ArrayList<Integer>();
		ListNode cur = head;
		exist.add(cur.val);
		
		while (cur.next != null) {
			if (exist.contains(cur.next.val)) {
				cur.next = cur.next.next;
			} else {
				exist.add(cur.next.val);
				cur = cur.next;
			}
		}
		
		return head;
	}
	
	ListNode removeDups2(ListNode head) {
		ListNode cur = head;
		while (cur != null && cur.next != null) {
			ListNode comp = cur; // Fix this node, others will compare with it
			ListNode prev = cur; // Keep a note of prev in case of removal
			ListNode next = cur.next;
			while (next != null) {
				if (next.val != comp.val) {
					prev = next;
					next = next.next;
				} else {
					prev.next = next.next;
					next = next.next;
				}
			}
			cur = comp.next;
		}
		
		return head;
	}
}
