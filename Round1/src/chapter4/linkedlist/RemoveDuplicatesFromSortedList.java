package chapter4.linkedlist;
/**
 * 83. Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 * @author Lei
 *
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head; // dummy is always the 'head', head is the distinguished one
        ListNode cur = head.next; // Cursor is moving, The latter one
        while (cur != null) {
            while (cur != null && head.val == cur.val) {
                // Skip head.next
                cur = cur.next;
            }
            
            head.next = cur;
            head = head.next;
        }
        
        return dummy;
    }
    
    // This edition is cleaner
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }
        
        // Only cur here
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        
        return head;
    }
    
    // My new version
    public ListNode deleteDuplicates3(ListNode head) { 
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        
        return head;
    }  
}
