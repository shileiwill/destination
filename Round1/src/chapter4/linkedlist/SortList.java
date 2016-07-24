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
        ListNode leftHead = head;
        ListNode rightHead = mid.next;
        mid.next = null;
        
        ListNode leftResult = sortList(leftHead);
        ListNode rightResult = sortList(rightHead);
        
        ListNode mergeResult = mergeTwoLists(leftResult, rightResult);
        
        return mergeResult;
    }
    
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
        
        while (l1 != null) {
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
