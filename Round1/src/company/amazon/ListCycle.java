package company.amazon;

import chapter4.linkedlist.ListNode;

public class ListCycle {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n3;
		
		ListCycle lc = new ListCycle();
		ListNode res = lc.detectCycle(n1);
		System.out.println(res.val);
	}

	public ListNode detectCycle(ListNode a) {
	    ListNode meet = findMeetPoint(a);
	    
	    if (meet == null) {
	        return null;
	    }
	    
	    ListNode walk1 = a;
	    ListNode walk2 = meet;
	    
	    while (walk1 != walk2) {
	        walk1 = walk1.next;
	        walk2 = walk2.next;
	    }
	    
	    return walk1;
	}
	
	ListNode findMeetPoint(ListNode node) {
	    ListNode fast = node.next;
	    ListNode slow = node;
	    
	    while (fast != null && fast.next != null) {
	        if (slow == fast) {
	            return slow;
	        }
	        
	        slow = slow.next;
	        fast = fast.next.next;
	        
	    }
	    
	    return null;
	}
}
