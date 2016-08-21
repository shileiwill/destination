package chapter4.linkedlist;
/**
 * 148. Sort a linked list in O(n log n) time using constant space complexity.
 * @author Lei
 *
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        ListNode leftHead = head; // 这个其实没必要
        ListNode rightHead = mid.next; // 这个非常必要
        mid.next = null; // Must
        
        ListNode leftResult = sortList(leftHead);
        ListNode rightResult = sortList(rightHead);
        
        ListNode mergeResult = mergeTwoLists(leftResult, rightResult);
        
        return mergeResult;
    }
    
    // How to find middle
    ListNode findMiddle(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
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
        
        while (l1 != null) { // Here use if is enough
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }
        
        while (l2 != null) {
            cur.next = l2;
            l2= l2.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}
