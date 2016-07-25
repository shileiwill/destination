package chapter4.linkedlist;

import java.util.ArrayList;
import java.util.List;
/**
 * 141. Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 * @author Lei
 *
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return false;
    }
    
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        List<ListNode> visited = new ArrayList<ListNode>();
        
        while (head != null) {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next;
        }
        
        return false;
    }
}
