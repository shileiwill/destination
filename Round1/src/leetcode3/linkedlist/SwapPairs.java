package leetcode3.linkedlist;
/**
 * 24. Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
import java.util.Stack;

import chapter4.linkedlist.ListNode;

public class SwapPairs {
	
    public ListNode swapPairsAnotherNewVersion02_20_2017(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            ListNode next = head.next.next;
            
            first.next = null;
            
            pre.next = second;
            second.next = first;
            pre = first;
            
            head = next;
        }
        
        if (head != null) {
            pre.next = head;
        }
        
        return dummy.next;
    }
    
    public ListNode swapPairsNew(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            
            ListNode first = head.next;
            ListNode second = head;
            second.next = next; // Dont need to set to null;
            first.next = second;
            
            pre.next = first;
            head = next;
            pre = second;
        }
        
        // if (head != null) {
        //     pre.next = head;
        // }
        
        return dummy.next;
    }
    
	public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            
            ListNode first = head.next;
            ListNode second = head;
            second.next = null;
            first.next = second;
            
            pre.next = first;
            head = next;
            pre = second;
        }
        
        if (head != null) {
            pre.next = head;
        }
        
        return dummy.next;
    }
    
    public ListNode swapPairs(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        boolean isFull = false;
        while (head != null) {
            while (!stack.isEmpty()) {
                ListNode node = stack.pop();
                node.next = null;
                pre.next = node;
                pre = pre.next;
            }
            for (int i = 0; i < 2 && head != null; i++) {
                stack.push(head);
                head = head.next;
            }
        }
        
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = null;
            pre.next = node;
            pre = pre.next;
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
//		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
		
		SwapPairs rll = new SwapPairs();
		ListNode res = rll.swapPairs(node1);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
