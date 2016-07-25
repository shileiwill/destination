package chapter4.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class FindMiddleOfList {

	public static void main(String[] args) {
		ListNode n0 = null;
		ListNode n1 = new ListNode(-1);
		ListNode n2 = new ListNode(5);
		ListNode n3 = new ListNode(11);
		
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(10);
		
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		
		n1.next = n2;
		n2.next = n3;
		
//		n3.next = n4;
		n4.next = n5;
//		n5.next = n6;
//		n6.next = n5;
		
		ListNode[] lists = {n0, n1, null, n4};
		FindMiddleOfList f = new FindMiddleOfList();
		f.test1(n1);
		f.test2(n1);
	}
	void test1(ListNode head) {
		head = head.next;
	}
	
	void test2(ListNode head) {
		System.out.println(head.val);
	}
	
    public ListNode mergeKLists(ListNode[] lists) {
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
        ListNode leftHead = helper(lists, 0, mid);
        ListNode rightHead = helper(lists, mid + 1, right);
        
        ListNode mergeHead = mergeTwoLists(leftHead, rightHead);
        
        return mergeHead;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
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
    
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        List<ListNode> visited = new ArrayList<ListNode>();
        
        while (head != null) {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next;
        }
        
        return false;
    }
	
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        
        while (head != null) {
            while (head != null && head.val == val) { // If equals, need to skip
                head = head.next;
            }
            pre.next = head;
            pre = head;
            
            if (head != null) {
                head = head.next;
            }
        }
        
        return dummy.next;
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
    
    public ListNode mergeTwoLists9(ListNode l1, ListNode l2) {
        
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
