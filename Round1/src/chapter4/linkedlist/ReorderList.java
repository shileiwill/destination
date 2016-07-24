package chapter4.linkedlist;
/**
 * 143. Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * @author Lei
 *
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        ListNode mid = findMiddle(head);
        
        ListNode leftHead = head;
        ListNode rightHead = mid.next;
        mid.next = null;
        
        ListNode rightNewHead = reverseLinkedList(rightHead);
        
        ListNode res = mergeTwoLists(leftHead, rightNewHead);
        
        head = res;
    }
    
    ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // Here must be null, not head
        ListNode prev = null;
        
        while(head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        boolean isFromL1 = true;
        while (l1 != null && l2 != null) {
            if (isFromL1) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            
            isFromL1 = !isFromL1;
            cur = cur.next;
        }
        
        // Dont need to use while!
        if (l1 != null) {
            cur.next = l1;
        } 
        
        if (l2 != null) {
            cur.next = l2;
        }
        
        return dummy.next;
    }
    
    ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
