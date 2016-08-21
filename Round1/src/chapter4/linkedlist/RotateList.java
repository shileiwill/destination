package chapter4.linkedlist;
/**
 * 61. Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

可以尝试一下reverse. reverse(left), reverse(right), reverse(all)
 */
public class RotateList {

	public static void main(String[] args) {
		// 1->2->3->2->1->null, 1
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(2);
		ListNode node5 = new ListNode(1);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		RotateList t = new RotateList();
		ListNode res = t.rotateRight(node1, 999);
		
		while (res != null) {
			System.out.print(res.val + "--");
			res = res.next;
		}
	}
	public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = findLength(head);
        k = k % len;
        if (k == 0) {
            return head;
        }
        ListNode preRightHead = findKth(head, k); // Can be K or K+1
        if (preRightHead == null) {
            return head;
        }
        ListNode rightHead = preRightHead.next;
        
        preRightHead.next = null;
        
        // Go to the end of rightHead
        ListNode rightEnd = findEnd(rightHead);
        rightEnd.next = head;
        
        return rightHead;
    }
    
    ListNode findEnd(ListNode head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
    
    ListNode findKth(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        
        for (int i = 1; i <= k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) { // Can be fast != null OR fast.next != null, depending on above k/k+1
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    int findLength2(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    // Another quicker way to find length
    int findLength(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;
        
        int size = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; //都是偶数
            size++;
        }
        
        size = size * 2;
        if (fast != null) {
            size++;//如果是奇数，加上1
        }
        System.out.println(size);
        return size;
    }
}
