package chapter4.linkedlist;

import java.util.Stack;

public class ReverseLinkedList {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		node1.next = node2;
		
		ReverseLinkedList rll = new ReverseLinkedList();
		rll.reverseList(node1);
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
