package company.linkedin;

import chapter3.binaryTree.TreeNode;

public class TreeUpsideDown {

	public static void main(String[] args) {

	}

	public TreeNode upsideDownBinaryTree(TreeNode root) {
	    TreeNode curr = root;
	    TreeNode next = null;
	    TreeNode temp = null;
	    TreeNode prev = null;
	    
	    while(curr != null) {
	        next = curr.left;
	        
	        // swapping nodes now, need temp to keep the previous right child
	        curr.left = temp;
	        temp = curr.right;
	        curr.right = prev;
	        
	        prev = curr;
	        curr = next;
	    }
	    return prev;
	}  
	
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) { // only root.left == null?
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
