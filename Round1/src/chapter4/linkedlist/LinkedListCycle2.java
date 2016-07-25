package chapter4.linkedlist;
/**
 * 142. Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.
 * @author Lei
 *
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                // When slow and fast meet, start walking slow and head
                while (slow.next != head) {
                    slow = slow.next;
                    head = head.next;
                }
                
                return head;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return null;
    }
}
