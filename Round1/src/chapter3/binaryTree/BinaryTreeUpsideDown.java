package chapter3.binaryTree;
/**
 * 156. Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root. 
For example:
 Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5



return the root of the binary tree [4,5,2,#,#,3,1].

   4
  / \
 5   2
    / \
   3   1  

 */

// There is also an iteration implementation. https://discuss.leetcode.com/topic/5961/easy-o-n-iteration-solution-java
public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
    
        TreeNode newNode = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        
        root.left = null;
        root.right = null;
        
        return newNode;
    }
    
    /**
     * Just think about how you can save the tree information 
you need before changing the tree structure. This is quite similar to reversing a linked list
     */
    public TreeNode UpsideDownBinaryTree2(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;
        
        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
	//Unfortunately, my solution doesnt work
//	public TreeNode upsideDownBinaryTree(TreeNode root) {
//		if (root == null || (root.left == null && root.right == null)) {
//			return root;
//		}
//		ResultType res = helper(root);
//		return res.parent;
//	}
//
//	ResultType helper(TreeNode root) {
//		if (root == null) {
//			return null;
//		}
//
//		if (root.left == null && root.right == null) {
//			return new ResultType(null, root);
//		}
//
//		ResultType left = helper(root.left);
//		ResultType right = helper(root.right);
//
//		// left will never be null
//		left.node.left = right == null ? null : right.node;
//		left.node.right = root;
//
//		root.left = null;
//		root.right = null;
//
//		return new ResultType(left.node, root);
//	}
//	
//	class ResultType {
//		TreeNode parent;
//		TreeNode node;
//
//		ResultType(TreeNode parent, TreeNode node) {
//			this.parent = parent;
//			this.node = node;
//		}
//	}
}
