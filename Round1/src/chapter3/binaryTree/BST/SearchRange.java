package chapter3.binaryTree.BST;

import java.util.ArrayList;

import chapter3.binaryTree.TreeNode;
/**
 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Return all the keys in ascending order.

Have you met this question in a real interview? Yes
Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
 * @author Lei
 *
 */
public class SearchRange {
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(res, root, k1, k2);        
        return res;
    }
    
    void helper(ArrayList<Integer> res, TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        // Must go left first. Be careful with > k1 and < k2
        if (root.val > k1) {
            helper(res, root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            res.add(root.val);
        }
        if (root.val < k2) {
            helper(res, root.right, k1, k2);
        } 
    }
}
