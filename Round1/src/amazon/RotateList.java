package amazon;

import chapter4.linkedlist.ListNode;

public class RotateList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		int k = 2;
		
		ListNode res = rotateRight(n1, k);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
	
	public ListNode rotateRightBetter(ListNode a, int b) {
	    ListNode dummy = new ListNode(-1);
	    ListNode cur = dummy;
	    int len = 0;
	    
	    ListNode node = a;
	    while (node != null) {
	        len++;
	        node = node.next;
	    }
	    
	    b = b % len;
	    
	    node = a;
	    int i = 1;
	    while (i < len - b) {
	        node = node.next;
	        i++;
	    }
	    ListNode pre = node;
	    node = node.next;
	    cur.next = node;
	    pre.next =null;
        
        while (cur.next != null) {
            cur = cur.next;
        }
        
        cur.next = a;
	    
	    return dummy.next;
	}

	public static ListNode rotateRight(ListNode a, int b) {
	    ListNode dummy = new ListNode(-1);
	    ListNode cur = dummy;
	    int len = 0;
	    
	    ListNode node = a;
	    while (node != null) {
	        len++;
	        node = node.next;
	    }
	    
	    b = b % len;
	    
	    node = a;
	    int i = 1;
	    while (i <= len - b) {
	        node = node.next;
	        i++;
	    }
	    
	    cur = dummy;
	    while (node != null) {
	        cur.next = node;
	        node = node.next;
	        cur = cur.next;
	    }
	    
	    node = a;
	    i = 1;
	    while (i <= len - b) {
	        cur.next = node;
	        node = node.next;
	        cur = cur.next;
	        cur.next = null;
	        i++;
	    }
	    cur.next = null;
	    
	    return dummy.next;
	}
}
