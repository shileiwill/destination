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
        
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        return prev;
    }

    TreeNode sortedArrayToBST(int[] arr) {
    	return helper(arr, 0, arr.length - 1);
    }
    
    TreeNode helper(int[] arr, int left, int right) {
    	if (left > right) {
    		return null;
    	}
    	
    	int mid = (left + right) / 2;
    	TreeNode root = new TreeNode(arr[mid]);
    	
    	root.left = helper(arr, left, mid - 1);
    	root.right = helper(arr, mid + 1, right);
    	
    	return root;
    }
}
