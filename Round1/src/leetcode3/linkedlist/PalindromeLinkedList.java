package leetcode3.linkedlist;

import java.util.ArrayList;
import java.util.List;

import chapter4.linkedlist.ListNode;

/**
 * 234. Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
	
    // Iteration
    ListNode left = null;
    public boolean isPalindrome0(ListNode head) {
        left = head;
        boolean res = helper(head);
        return res;
    }
    
    boolean helper(ListNode head) {
        if (head == null) {
            return true;
        }
        
        boolean res = helper(head.next); // Right
        
        if (!res) {
            return false;
        }
        
        if (head.val != left.val) {
            return false;
        }
        
        left = left.next; // Left Move forward
        
        return true;
    }
    
    // O(N) space and time
	public boolean isPalindrome(ListNode head) {
        ListNode reversedHead = reverse(head);
        
        while (head != null) {
            if ((head.val != reversedHead.val)) {
                return false;
            }
            head = head.next;
            reversedHead = reversedHead.next;
        }
        return true;
    }
    
    ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode pre = new ListNode(head.val);
        while (head.next != null) {
            ListNode temp = new ListNode(head.next.val);
            temp.next = pre;
            pre = temp;
            head = head.next;
        }
        
        return pre;
    }
    
    public boolean isPalindrome2(ListNode head) {
        List<Integer> list = new ArrayList<Integer>();
        
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        
        int left = 0, right = list.size() - 1;
        
        while (left < right) {
            if (list.get(left) - list.get(right) != 0) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
