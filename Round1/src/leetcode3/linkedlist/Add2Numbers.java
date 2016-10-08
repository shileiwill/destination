package leetcode3.linkedlist;
/**
 * 2. You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */
import chapter4.linkedlist.ListNode;

public class Add2Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curNode = dummy;
        
        int addition = 0;
        while (l1 != null && l2 != null) {
            int total = l1.val + l2.val + addition;
            int curDigit = total % 10;
            addition = total / 10;
            
            curNode.next = new ListNode(curDigit);
            curNode = curNode.next;
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            int total = l1.val + addition;
            int curDigit = total % 10;
            addition = total / 10;
            
            curNode.next = new ListNode(curDigit);
            curNode = curNode.next;
            
            l1 = l1.next;
        }
        
        while (l2 != null) {
            int total = l2.val + addition;
            int curDigit = total % 10;
            addition = total / 10;
            
            curNode.next = new ListNode(curDigit);
            curNode = curNode.next;
            
            l2 = l2.next;
        }
        
        if (addition != 0) {
            curNode.next = new ListNode(addition);
            // Dont need to go to next any more.
        }
        
        return dummy.next;
    }
}
