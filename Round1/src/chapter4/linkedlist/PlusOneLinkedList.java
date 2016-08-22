package chapter4.linkedlist;
/**
 * 369. Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:

Input:
1->2->3

Output:
1->2->4

 */
import java.util.ArrayList;
import java.util.List;

public class PlusOneLinkedList {

	public static void main(String[] args) {

	}

    public ListNode plusOne(ListNode head) {
        List<Integer> list = new ArrayList<Integer>();
        
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == 9) {
                if (i == 0) {
                    list.set(i, 1);
                    list.add(0);
                } else {
                    list.set(i, 0);
                }
            } else {
                list.set(i, list.get(i) + 1);
                break;
            }
        }
        
        ListNode dummy = new ListNode(-1);
        cur = dummy;
        for (int i = 0; i < list.size(); i++) {
            cur.next = new ListNode(list.get(i));
            cur = cur.next;
        }
        return dummy.next;
    }
    
    // Without using extra space
    public ListNode plusOneReverse(ListNode head) {
        ListNode reversed = reverse(head);
        
        ListNode rHead = reversed;
        ListNode cur = rHead;
        while (cur != null) {
            if (cur.val + 1 <= 9) {
                cur.val = cur.val + 1;
                break;
            } else {
                cur.val = 0;
                if (cur.next == null) { // The last node
                    cur.next = new ListNode(1);
                    break;
                } else {
                    cur = cur.next;
                }
            }
        }
        
        return reverse(rHead);
    }
    
    ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}
