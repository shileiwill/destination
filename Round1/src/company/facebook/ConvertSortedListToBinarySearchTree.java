package company.facebook;
// 重要 Tree To Doubly Linked List; Doubly Linked List to Balanced Tree
import chapter3.binaryTree.TreeNode;
import chapter4.linkedlist.ListNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBinarySearchTree {
	
	public static void main(String[] args) {
		ConvertSortedListToBinarySearchTree c = new ConvertSortedListToBinarySearchTree();
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
//		TreeNode root = c.sortedListToBST(node1);
		
		int[] arr = {1, 2, 3, 4};
		
		TreeNode root = c.sortedArrayToBST(arr);
		
		System.out.println(root.val);
	}

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode preMid = findMid(head);
        
        if (preMid == null) { // There are only 2 elements
        	TreeNode root = new TreeNode(head.val);
        	root.right = sortedListToBST(head.next);
        	
        	return root;
        } else { // At least 3 elements, need left and right
            TreeNode root = new TreeNode(preMid.next.val);
            
            ListNode left = head;
            ListNode right = preMid.next.next;
            preMid.next = null;
            
            root.left = sortedListToBST(left);
            root.right = sortedListToBST(right);
            
            return root;
        }
    }
    
    // If there are only 2 elements, pre will be null
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
