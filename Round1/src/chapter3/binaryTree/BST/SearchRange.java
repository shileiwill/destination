package chapter3.binaryTree.BST;

import java.util.ArrayList;
import java.util.Stack;

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
    // This version is more efficient, as we can skip <k1 and >k2
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
    
    // Another traversal iteration version
    public ArrayList<Integer> searchRange2(TreeNode root, int k1, int k2) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            if (node.val >= k1 && node.val <= k2) {
                res.add(node.val);
            }
            if (node.val > k2) { // If it is too big, just stop
                break;
            }
            cur = node.right;
        }
        
        return res;
    }
}
