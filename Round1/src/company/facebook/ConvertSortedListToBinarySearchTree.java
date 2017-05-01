package company.facebook;

import chapter3.binaryTree.TreeNode;
import chapter4.linkedlist.ListNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode preMid = findMid(head);
        TreeNode root = new TreeNode(preMid.next.val);
        
        ListNode left = head;
        ListNode right = preMid.next.next;
        preMid.next = null;
        
        root.left = sortedListToBST(left);
        root.right = sortedListToBST(right);
        
        return root;
    }
    
    ListNode findMid(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode prev = slow;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        return prev;
    }

}
