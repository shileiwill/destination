package chapter4.linkedlist;
/**
 * 21. Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * @author Lei
 *
 */
public class Merge2SortedList {
	// Dont need to new
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        // Dont need to use while!
        if (l1 != null) {
            cur.next = l1;
        } 
        
        if (l2 != null) {
            cur.next = l2;
        }
        
        // Both of them must reach null eventually
        // l1.next = null;
        // l2.next = null;
        
        return dummy.next;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        while (l1 != null) {
            cur.next = new ListNode(l1.val);
            l1 = l1.next;
            cur = cur.next;
        }
        
        while (l2 != null) {
            cur.next = new ListNode(l2.val);
            l2= l2.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}
