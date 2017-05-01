package chapter4.linkedlist;

import java.util.Stack;
/**
 * 206. Reverse Linked List
 */
public class ReverseLinkedList {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		node1.next = node2;
		
		ReverseLinkedList rll = new ReverseLinkedList();
		rll.reverseRecursion(node1);
		
		while (rll.res != null) {
			System.out.println(rll.res.val);
			rll.res = rll.res.next;
		}
	}
	// In place. Swap every 2. Most important thing is to use Prev node
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = null;
        
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
    
    // Use recursion!
    ListNode pre = null;
    ListNode res = null;
    void reverseRecursion(ListNode node) {
    	if (node == null) {
    		return;
    	}
    	reverseRecursion(node.next);
    	
    	if (pre == null) {
    		pre = node;
    		res = node;
    	} else {
    		pre.next = node;
    		pre = node;
    		pre.next = null; // Set null
    	}
    }
    
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        
        while (!stack.isEmpty()) {
            ListNode aNode = stack.pop();
            aNode.next = null; // This is where it fails the first time!!!
            node.next = aNode;
            node = node.next;
        }
        
        return dummy.next;
    }
}
