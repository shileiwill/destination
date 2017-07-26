package company.facebook.others;

/**
 * Created by weixwu on 7/10/2017.
 * Binary search tree to circular linked list, using TreeNode in place
 */
public class BSTtoLinkedList {
	// This solution will make it circular, but hard to understand
    public TreeNode bstToLinkedList(TreeNode root){
        if (root == null) {
        	return null;
        }
        
        TreeNode left = bstToLinkedList(root.left);
        if (left != null){
            root.prev = left.prev;
            left.prev.next = root;
        }
        
        TreeNode right = bstToLinkedList(root.right);
        
        if (left == null && right == null) { // Leaf Node, No left or right node
            root.next = root;
            root.prev = root;
            
            return root;
        }
        
        if (left != null && right != null) { // Has both left and right child nodes
            left.prev = right.prev;
            right.prev.next = left;
        }
        
        if (left == null){
            root.prev = right;
            right.next = root;
        }
        
        if (right == null){
            left.prev = root;
            root.next = left;
        }
        
        if (right != null){
            root.next = right;
            right.prev = root;
        }
        
        return left != null ? left : root;
    }

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        TreeNode prev;
        TreeNode next;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    // It is doubly linked list, not circular. You can just join the 2 ends to make it circular
    public TreeNode bstToDll(TreeNode node){
    	if (node == null) {
    		return null;
    	}
    	
    	TreeNode leftHead = bstToDll(node.left); // traverse down to left 
    	TreeNode rightHead = bstToDll(node.right); // traverse down to right
    	
        /*
         * lefthead represents head of link list created in left of node
         * righthead represents head of link list created in right
         * travel to end of left link list and add the current node in end
         */
        if(leftHead == null) {
            leftHead = node;
        } else {
        	TreeNode cur = leftHead;
        	while(cur.next != null){
        		cur = cur.next;
        	}
        	// Go to last node on the left side, connect
        	cur.next = node;
        	node.prev = cur;
        }
        
        /*
         *set the next node of current root to right head of right list
         */
        if(rightHead == null){ // Do we need to set this?
            rightHead = node;
        } else {
        	node.next = rightHead;
        	rightHead.prev = node;
        }
        
        return leftHead;// return left head as the head of the list added with current node
}

    public static void main(String args[]){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(11);
        //root.right.right = new TreeNode(14);
        BSTtoLinkedList sl = new BSTtoLinkedList();
        TreeNode res = sl.bstToDll(root);
        
        while (res != null) {
        	System.out.println(res.val);
        	res = res.next;
        }
    }
}
