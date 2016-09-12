package cc150.chapter2.linkedlist;

import java.util.Stack;

import chapter4.linkedlist.ListNode;

public class SumLists {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(7);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		ListNode node4 = new ListNode(9);
		ListNode node5 = new ListNode(8);
		ListNode node6 = new ListNode(2);
		ListNode node7 = new ListNode(9);
		
		node1.next = node2;
		node2.next = node3;
		
//		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		SumLists rd = new SumLists();
		ListNode res = rd.sumListsReverse(node1, node4);
		
		ListNode reversedRes = reverse(res);
		
		while (reversedRes != null) {
			System.out.print(reversedRes.val + " -- ");
			reversedRes = reversedRes.next;
		}
	}
	
	// How to reverse a linkedlist. Most important thing is to use PRE node
	static ListNode reverse(ListNode head) {
		ListNode pre = null;
		
		while (head != null) {
			ListNode temp = head.next;
			head.next = pre;
			pre = head;
			head = temp;
		}
		
		return pre;
	}

	ListNode sumLists(ListNode left, ListNode right) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		
		int remainder = 0;
		while (left != null && right != null) { 
			int valLeft = left.val;
			int valRight = right.val;
			
			int sum = valLeft + valRight + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
			
			left = left.next;
			right = right.next;
		}
		
		while (left != null) {
			int valLeft = left.val;
			int sum = valLeft + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
			
			left = left.next;
		}
		
		while (right != null) {
			int valRight = right.val;
			int sum = valRight + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
			
			right = right.next;
		}
		
		if (remainder != 0) {
			cur.next = new ListNode(remainder);
		}
		
		return dummy.next;
	}
	
	ListNode sumListsReverse(ListNode left, ListNode right) {
		Stack<Integer> leftStack = new Stack<Integer>();
		Stack<Integer> rightStack = new Stack<Integer>();
		
		while (left != null) {
			leftStack.push(left.val);
			left = left.next;
		}
		
		while (right != null) {
			rightStack.push(right.val);
			right = right.next;
		}
		
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		
		int remainder = 0;
		while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
			int valLeft = leftStack.pop();
			int valRight = rightStack.pop();
			
			int sum = valLeft + valRight + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
		}
		
		while (!leftStack.isEmpty()) {
			int valLeft = leftStack.pop();
			int sum = valLeft + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
		}
		
		while (!rightStack.isEmpty()) {
			int valRight = rightStack.pop();
			int sum = valRight + remainder;
			int curDigit = sum % 10;
			remainder = sum < 10 ? 0 : 1;
			
			cur.next = new ListNode(curDigit);
			cur = cur.next;
		}
		
		if (remainder != 0) {
			cur.next = new ListNode(remainder);
		}
		
		return dummy.next;
	}
	
	// A recursive approach
	class PartialSum {
		ListNode node = null;
		int carry = 0;
	}
	
	ListNode addLists(ListNode l1, ListNode l2) {
		int len1 = getLength(l1);
		int len2 = getLength(l2);
		
		if (len1 < len2) {
			l1 = padList(l1, len2 - len1);
		} else {
			l2 = padList(l2, len1 - len2);
		}
		
		PartialSum res = addListsHelper(l1, l2);
		
		// Leftover
		if (res.carry == 0) {
			return res.node;
		} else {
			ListNode l = insertBefore(res.node, res.carry);
			return l;
		}
	}
	
	// the 2 listnodes have same length
	PartialSum addListsHelper(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return new PartialSum(); // Empty
		}
		
		PartialSum ps = addListsHelper(l1.next, l2.next);
		
		int curSum = l1.val + l2.val + ps.carry;
		int curDigit = curSum % 10;
		int nextCarry = curSum / 10;
		
		ListNode node = insertBefore(ps.node, curDigit);
		PartialSum newPS = new PartialSum();
		newPS.node = node;
		newPS.carry = nextCarry;
		
		return newPS;
	}
	
	ListNode padList(ListNode node, int num) {
		ListNode head = node;
		for (int i = 0; i < num; i++) {
			head = insertBefore(head, 0);
		}
		return head;
	}
	
	ListNode insertBefore(ListNode node, int val) {
		ListNode newNode = new ListNode(val);
		newNode.next = node;
		return newNode;
	}
}
