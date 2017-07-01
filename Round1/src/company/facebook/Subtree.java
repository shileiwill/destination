package company.facebook;

import chapter3.binaryTree.TreeNode;

/**
 * You have two every large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.

 Notice

A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.

Have you met this question in a real interview? Yes
Example
T2 is a subtree of T1 in the following case:

       1                3
      / \              / 
T1 = 2   3      T2 =  4
        /
       4
T2 isn't a subtree of T1 in the following case:

       1               3
      / \               \
T1 = 2   3       T2 =    4
        /
       4
 */
public class Subtree {

    /**
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        if (T2 == null) {
            return true;
        }
        
        if (T1 == null) {
            return false;
        }
        
        
        if (T1.val == T2.val) {
            if (helper(T1, T2)) {
                return true;
            }
        }
        
        return isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
    }
    
    boolean helper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        
        if (node1 == null || node2 == null) {
            return false;
        }
        
        if (node1.val != node2.val) {
            return false;
        }
        
        return helper(node1.left, node2.left) && helper(node1.right, node2.right);
    }

}
