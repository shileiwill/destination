package company.yahoo;

import java.util.Stack;

import chapter4.linkedlist.ListNode;

public class SwapPairInLinkedList {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		
		SwapPairInLinkedList swap = new SwapPairInLinkedList();
		ListNode head = swap.swapStack(node1, 3);
		
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	ListNode swapValues(ListNode node) {
		ListNode cur = node;
		ListNode prev = cur;
		cur = cur.next;
		
		while (cur != null) {
			int tmp = prev.val;
			prev.val = cur.val;
			cur.val = tmp;
			
			prev = cur.next;
			if (cur.next != null) {
				cur = cur.next.next;
			} else {
				break;
			}
		}
		
		return node;
	}
	
	
	ListNode swap2(ListNode node) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		
		while (node != null && node.next != null) {
			ListNode first = node;
			ListNode second = node.next;
			ListNode next = node.next.next;
			
			first.next = null;
			prev.next = second;
			second.next = first;
			
			prev = first;
			node = next;
		}
		
		if (node != null) {
			prev.next = node;
		}
		
		return dummy.next;
	}
	
	ListNode swapStack(ListNode node, int k) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		Stack<ListNode> stack = new Stack<ListNode>();
		
		while (node != null) {
			while (!stack.isEmpty()) {
				ListNode now = stack.pop();
				now.next = null;
				prev.next = now;
				prev = prev.next;
			}
			
			for (int i = 0; i < k && node != null; i++) {
				stack.push(node);
				node = node.next;
			}
		}
		
		while (!stack.isEmpty()) { // If we dont want to reverse the last section, reverse the stack here
			ListNode now = stack.pop();
			now.next = null;
			prev.next = now;
			prev = prev.next;
		}
		
		return dummy.next;
	}
}
