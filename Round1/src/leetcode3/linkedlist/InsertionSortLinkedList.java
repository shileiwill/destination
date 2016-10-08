package leetcode3.linkedlist;

import chapter4.linkedlist.ListNode;

public class InsertionSortLinkedList {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(1);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
		
		InsertionSortLinkedList rll = new InsertionSortLinkedList();
		ListNode res = rll.insertionSortList(node1);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
	
    public ListNode insertionSortList(ListNode head) {
        ListNode res = helper(head);
        return res;
    }
    
    ListNode helper(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode node = helper(head.next); // Node is actually not used
        ListNode cur = head;
        while (cur != null && cur.next != null && cur.val > cur.next.val) {
            int temp = cur.val;
            cur.val = cur.next.val;
            cur.next.val = temp;
            
            cur = cur.next;
        }
        
        return head;
    }
    
    public ListNode insertionSortListIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode next = null;
        
        while (cur != null) {
            next = cur.next;
            
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next; // Moving forward
            }
            
            // Insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = dummy;
            cur = next;
        }
        
        
        return dummy.next;
    }
}
