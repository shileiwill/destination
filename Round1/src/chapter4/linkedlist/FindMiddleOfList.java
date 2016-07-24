package chapter4.linkedlist;

import chapter3.binaryTree.TreeNode;

public class FindMiddleOfList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
//		n4.next = n5;
//		n5.next = n6;
//		n6.next = n7;
		
		new FindMiddleOfList().reorderList(n1);
//		System.out.println(res.val);
	}
	
	
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        ListNode mid = findMiddle(head);
        
        ListNode leftHead = head;
        ListNode rightHead = mid.next;
        mid.next = null;
        
        ListNode rightNewHead = reverseLinkedList(rightHead);
        
        ListNode res = mergeTwoLists(leftHead, rightNewHead);
        
        head = res;
    }
    
    ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Here must be null, not head
        ListNode prev = null;
        
        while(head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        boolean isFromL1 = true;
        while (l1 != null && l2 != null) {
            if (isFromL1) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            
            isFromL1 = !isFromL1;
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
    
    ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    
	static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = head;
        
        while(head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
	
	static ListNode findMiddle2(ListNode head) {
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

}
