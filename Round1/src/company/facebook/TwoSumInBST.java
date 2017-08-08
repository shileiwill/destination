package company.facebook;

import java.util.HashSet;
import java.util.Set;

import chapter3.binaryTree.TreeNode;
/**
 * 653. Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 */
public class TwoSumInBST {
    public boolean findTargetMyOwn(TreeNode root, int k) {
        TreeNode cur = root;
        return helper(root, cur, k);
    }
    
    boolean helper(TreeNode root, TreeNode cur, int k) {
        if (cur == null) {
            return false;
        }
        
        int toFind = k - cur.val;
        
        if (isInBST(root, toFind, cur)) {
            return true;
        } else {
            return helper(root, cur.left, k) || helper(root, cur.right, k);
        }
    }
    
    boolean isInBST(TreeNode root, int target, TreeNode source) {
        TreeNode cur = root;
        
        while (cur != null) {
            if (cur.val == target && source != cur) {
                return true;
            }
            
            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        
        return false;
    }
    
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<Integer>();
        return helper2(root, k, set);
    }
    
    boolean helper2(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        
        return helper2(root.left, k, set) || helper2(root.right, k, set);
    }
}
