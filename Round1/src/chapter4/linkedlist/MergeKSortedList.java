package chapter4.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class MergeKSortedList {
	
	// Divide and Conquer
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return helper(lists, 0, lists.length - 1);
    }
    
    ListNode helper(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        
        int mid = left + (right - left) / 2;
        ListNode leftHead = helper(lists, left, mid);
        ListNode rightHead = helper(lists, mid + 1, right);
        
        ListNode mergeHead = mergeTwoLists(leftHead, rightHead);
        
        return mergeHead;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
        
        return dummy.next;
    }
	
	// With Heap
    class HeapComparator implements Comparator<ListNode> {

		@Override
		public int compare(ListNode left, ListNode right) { // This will make smallest comes first
			return left.val - right.val;
		}
        
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new HeapComparator());
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        // Add all start node to heap
        for (int i = 0; i < lists.length; i++) {
        	ListNode node = lists[i];
        	if (node != null) { // Add only non-null element
        		heap.add(node);
        	}
        }
        
        while (!heap.isEmpty()) {
        	ListNode node = heap.poll();
        	if (node.next != null) {
        		heap.add(node.next);
        	}
        	
        	cur.next = node;
        	cur = cur.next;
        }
        
        return dummy.next;
    }
}
