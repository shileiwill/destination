package chapter4.linkedlist;

import chapter3.binaryTree.TreeNode;
/**
 * 109. Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * @author Lei
 *
 */
public class ConvertSortedListToBST {

	public static void main(String[] args) {
		//[3,5,8]
		ListNode n1 = new ListNode(3);
		ListNode n2 = new ListNode(5);
		ListNode n3 = new ListNode(8);
		
		n1.next = n2;
		n2.next = n3;
		
		TreeNode res = new ConvertSortedListToBST().sortedListToBST(n1);
		System.out.println(res.val);
	}

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode preMidListNode = findPreMiddle(head);
        ListNode rightHead = preMidListNode.next.next;
        
        TreeNode midTreeNode = new TreeNode(preMidListNode.next.val);
        midTreeNode.right = sortedListToBST(rightHead); // Let's make right first, just in case
        preMidListNode.next = null; // Must terminate by using null
        midTreeNode.left = sortedListToBST(head);
        
        return midTreeNode;
    }
    
    ListNode findPreMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        if (fast != null && fast.next != null) { // Because we want the one before mid
            fast = fast.next.next;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}
