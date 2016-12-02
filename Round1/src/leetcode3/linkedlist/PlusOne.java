package leetcode3.linkedlist;
/**
 * 369. Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
 */
public class PlusOne {
    public ListNode plusOne(ListNode head) {
        ListNode rHead = reverse(head);
        
        ListNode pos = null;
        
        int addition = 1;
        while (rHead != null) {
            int curSum = rHead.val + addition;
            addition = curSum / 10;
            int curDigit = curSum % 10;
            
            ListNode curNode = new ListNode(curDigit);
            curNode.next = pos;
            pos = curNode;
            
            rHead = rHead.next;
        }
        
        if (addition != 0) {
            ListNode curNode = new ListNode(addition);
            curNode.next = pos;
            pos = curNode;
        }
        
        return pos;
    }
    
    ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        
        return prev;
    }
}
