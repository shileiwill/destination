package cc150.chapter2.linkedlist;

import java.util.Stack;

import chapter4.linkedlist.ListNode;

public class Palindrome {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(3);
		ListNode node6 = new ListNode(2);
		ListNode node7 = new ListNode(1);
		
		node1.next = node2;
		node2.next = node3;
		
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		Palindrome rd = new Palindrome();
		boolean res = rd.isPalindrome2(node1);
		
		System.out.print(res);
	}

	boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;
		
		Stack<ListNode> stack = new Stack<ListNode>();
		
		while (fast != null && fast.next != null) {
			stack.push(slow);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// The last slow is not push to stack, need to push if even number
		if (fast != null) { // Even number
			stack.push(slow);
		}
		
		slow = slow.next; // Start of the second half
		
		while (!stack.isEmpty()) {
			int leftVal = stack.pop().val;
			int rightVal = slow.val;
			
			if (leftVal != rightVal) {
				return false;
			}
			slow = slow.next;
		}
		
		return true;
	}
	
	// Reverse and Compare
	boolean isPalindrome2(ListNode head) {
		ListNode reversed = reverseAndClone(head);
		return compare(reversed, head);
	}
	
	ListNode reverseAndClone(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode cloneHead = new ListNode(head.val);
			ListNode temp = head.next;
			cloneHead.next = pre;
			pre = cloneHead;
			head = temp;
		}
		
		return pre;
	}
	
	boolean compare(ListNode reversed, ListNode head) {
		while (reversed != null) {
			if (reversed.val != head.val) {
				return false;
			}
			reversed = reversed.next;
			head = head.next;
		}
		
//		return true;
		return reversed == null && head == null;
	}
}
