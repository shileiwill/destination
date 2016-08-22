package chapter3.binaryTree.BST;

import chapter3.binaryTree.TreeNode;
/**
 * Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

 Notice

You can assume there is no duplicate values in this tree + node.

Have you met this question in a real interview? Yes
Example
Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
 * @author Lei
 *
 */
public class InsertNodeInBST {
	// Seems doesnt look very nice
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        if (node.val < root.val) {
            if (root.left == null) {
                root.left = node;
                return root;
            } else {
                insertNode(root.left, node); // Dont give return here, we want to return the initial root
            }
        } else {
            if (root.right == null) {
                root.right = node;
                return root;
            } else {
                insertNode(root.right, node);
            }
        }
        
        return root;
    }
    
    // This one is great!
    // This looks better, but not very straight forward to understand
    public TreeNode insertNode2(TreeNode root, TreeNode node) {
        if (root == null) {
            return node; // Return node when hit null, so root.left/right will be node eventually
        }
        
        if (node.val < root.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        
        return root;
   }
}
