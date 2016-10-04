package array;
/**
 * 106. Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */
import java.util.HashMap;
import java.util.Map;

import chapter3.binaryTree.TreeNode;

public class BuildBTFromInPostOrder {
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) { // Cache indexes of inorder, we can get left tree size, right tree size
            map.put(inorder[i], i);
        }
        
        TreeNode root = buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
        return root;
    }
    
    TreeNode buildTree2(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd, Map<Integer, Integer> map) {
        if (inorderStart > inorderEnd || postorderStart > postorderEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postorderEnd]);
        int rootIndexInorder = map.get(postorder[postorderEnd]);
        
        // Always remove 1 item from postorder. (rootIndexInorder - inorderStart) is the length of subtree
        root.left = buildTree2(inorder, inorderStart, rootIndexInorder - 1, postorder, postorderStart, postorderStart + rootIndexInorder - inorderStart - 1, map);
        root.right = buildTree2(inorder, rootIndexInorder + 1, inorderEnd, postorder, postorderStart + rootIndexInorder - inorderStart, postorderEnd - 1, map);
        
        return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        return root;
    }
    
    TreeNode buildTree(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd) {
        if (inorderStart > inorderEnd || postorderStart > postorderEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postorderEnd]);
        // Find index of root in inorder traversal
        int rootIndexInorder = findRootIndexInInorderTraversal(inorder, postorder[postorderEnd]);
        
        // Always remove 1 item from postorder. (rootIndexInorder - inorderStart) is the length of subtree
        root.left = buildTree(inorder, inorderStart, rootIndexInorder - 1, postorder, postorderStart, postorderStart + rootIndexInorder - inorderStart - 1);
        root.right = buildTree(inorder, rootIndexInorder + 1, inorderEnd, postorder, postorderStart + rootIndexInorder - inorderStart, postorderEnd - 1);
        
        return root;
    }
    
    // We dare to do this because there is no duplicate values guaranteed
    int findRootIndexInInorderTraversal(int[] inorder, int val) {
        int i = 0;
    	for (; i < inorder.length; i++) {
        	if (inorder[i] == val) {
        		return i;
        	}
        }
    	return -1;
    }
}
