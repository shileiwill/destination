package amazon;

import java.util.ArrayList;
import java.util.List;

import chapter4.linkedlist.ListNode;

public class ReorderList {

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
		
		ReorderList rl = new ReorderList();
		ListNode res = rl.reorderList(n1);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

	// Better
	public ListNode reorderListBetter(ListNode a) {
	    List<ListNode> list = new ArrayList<ListNode>();
	    
	    ListNode node = a;
	    while (node != null) {
	        list.add(node);
	        node = node.next;
	    }
	    
	    int left = 0, right = list.size() - 1;
	    int len = list.size();
	    
	    ListNode dummy = new ListNode(-1);
	    ListNode cur = dummy;
	    
	    while (left < right) {
	        ListNode first = list.get(left);
	        ListNode second = list.get(right);
	        
	        cur.next = first;
	        cur = cur.next;
	        cur.next = second;
	        cur = cur.next;
	        
	        left++;
	        right--;
	    }
	    
	    if (left == right) {
	        cur.next = list.get(left);
	        cur = cur.next;
	    }
	    
        cur.next = null;
        
        return dummy.next;
	}
	
	public ListNode reorderList(ListNode a) {
	    ListNode b = reverse(a);
	    
	    ListNode dummy = new ListNode(-1);
	    ListNode cur = dummy;
	    
	    int i = 0;
	    while (i < len / 2) {
	        cur.next = a;
	        cur = cur.next;
	        a = a.next; // This guy must be here, cannot be together with b = b.next
	        
	        cur.next = b;
	        cur = cur.next;
	        b = b.next;
	        
	        i++;
	    }
	    
	    if (len % 2 == 1) {
	        cur.next = a;
	        cur = cur.next;
	    }
	    
	    cur.next = null;
	    
	    return dummy.next;
	}
	
	int len = 0;
	ListNode reverse(ListNode node) {
	    ListNode prev = null;
	    while (node != null) {
	        len++;
	        ListNode next = node.next;
	        
	        ListNode now = new ListNode(node.val);
	        
	        now.next = prev;
	        prev = now;
	        node = next;
	    }
	    
	    return prev;
	}
}
