package chapter3.binaryTree;
/**
 * Convert a binary search tree to doubly linked list with in-order traversal.

Have you met this question in a real interview? Yes
Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5
 */
import java.util.Stack;

public class ConvertBSTToDoublyLinkedList {
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        Stack<TreeNode> stack = new Stack<TreeNode>();
        DoublyListNode dummy = new DoublyListNode(-1);
        DoublyListNode cur = dummy;
        
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            TreeNode node = stack.pop();
            
            DoublyListNode n = new DoublyListNode(node.val);
            cur.next = n;
            n.prev = cur;
            cur = n;
            
            root = node.right;
        }
        
        return dummy.next;
    }
    
    public class TreeNode {
    	    public int val;
    	    public TreeNode left, right;
    	    public TreeNode(int val) {
    	        this.val = val;
    	        this.left = this.right = null;
    	    }
    	 }
    public class DoublyListNode {
    	    int val;
    	    DoublyListNode next, prev;
    	    DoublyListNode(int val) {
    	        this.val = val;
    	        this.next = this.prev = null;
    	    }
    	}
}
