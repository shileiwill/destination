package leetcode3.linkedlist;

import java.util.Stack;

/**
 * 445. You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers {
	
	// Without reverse
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();
        Stack<ListNode> res = new Stack<ListNode>();
        
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        
        int addition = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int curSum = s1.pop().val + s2.pop().val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            res.push(new ListNode(curDigit));
        }
        
        while (!s1.isEmpty()) {
            int curSum = s1.pop().val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            res.push(new ListNode(curDigit));
        }
        
        while (!s2.isEmpty()) {
            int curSum = s2.pop().val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            res.push(new ListNode(curDigit));
        }
        
        if (addition != 0) {
            res.push(new ListNode(addition));
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode pos = dummy;
        
        while (!res.isEmpty()) {
            pos.next = res.pop();
            pos = pos.next;
        }
        
        return dummy.next;
    }

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        
        ListNode dummy = new ListNode(-1);
        ListNode pos = dummy;
        
        int addition = 0;
        while (r1 != null && r2 != null) {
            int curSum = r1.val + r2.val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            pos.next = new ListNode(curDigit);
            pos = pos.next;
            r1 = r1.next;
            r2 = r2.next;
        }
        
        while (r1 != null) {
            int curSum = r1.val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            pos.next = new ListNode(curDigit);
            pos = pos.next;
            r1 = r1.next;
        }
        
        while (r2 != null) {
            int curSum = r2.val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            pos.next = new ListNode(curDigit);
            pos = pos.next;
            r2 = r2.next;
        }
        
        if (addition != 0) {
            pos.next = new ListNode(addition);
        }
        
        return reverse(dummy.next);
    }
    
    ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}