package leetcode1.array;

import java.util.HashMap;
import java.util.Map;

import chapter3.binaryTree.TreeNode;

/**
 * 105.
 * Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */
public class BuildBTFromInPreOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        return root;
    }
    
    TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> map) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preorderStart]);
        int rootIndexInInorder = map.get(preorder[preorderStart]);
        
        // length of subtree == rootIndexInInorder - inorderStart
        int lenOfSubtree = rootIndexInInorder - inorderStart;
        root.left = buildTree(preorder, preorderStart + 1, preorderStart + lenOfSubtree, inorder, inorderStart, rootIndexInInorder - 1, map);
        root.right = buildTree(preorder, preorderStart + lenOfSubtree + 1, preorderEnd, inorder, rootIndexInInorder + 1, inorderEnd, map);
        
        return root;
    }
}
