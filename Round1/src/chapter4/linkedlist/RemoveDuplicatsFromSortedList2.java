package chapter4.linkedlist;
/**
 * 82. Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
 * @author Lei
 *
 */
public class RemoveDuplicatsFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                // See if there are more equals
                int pre = head.next.val;
                while (head.next != null && head.next.val == pre) {
                    head.next = head.next.next; // Jump jump
                }
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
    }
    
    // This is easier to understand
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        ListNode cur = head;
        ListNode next = cur.next;
        
        while (cur != null) {
            while (next != null && next.val == cur.val) {
                next = next.next; // Search
            }
            
            if (cur.next == next) { // Doesn't go through the while above, which means cur.val is different with next
                pre.next = cur; // Link
                pre = pre.next; // Move
            }
            
            cur = next; // Jump directly to the next different one
        }
        
        pre.next = null;
        return dummy.next;
    }
}
