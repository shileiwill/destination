package company.amazon.lintcode;

import chapter3.binaryTree.TreeNode;

/**
 * Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.
 Notice
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.
Have you met this question in a real interview? 
Yes
Example
Given a binary tree:
     1
   /   \
-5     2
/ \   /  \
0   3 -4  -5
return the node with value 3.
 */
public class MaximumSubtree {
    /**
     * @param root the root of binary tree
     * @return the maximum weight node
     */
    public TreeNode findSubtree(TreeNode root) {
        // Write your code here
        if (root == null) {
            return null;
        }
        helper(root);
        return max.node;
    }
   
    Wrapper max = null;
    Wrapper helper(TreeNode node) {
        if (node == null) {
            return new Wrapper(0, null);
        }
       
        Wrapper left = helper(node.left);
        Wrapper right = helper(node.right);
       
        int sum = left.sum + right.sum + node.val;
        Wrapper w = new Wrapper(sum, node);
       
        if (max == null || max.sum < w.sum) {
            max = w;
        }
       
        return w;
    }
}
 
class Wrapper {
    int sum = 0;
    TreeNode node;
   
    Wrapper(int sum, TreeNode node) {
        this.sum = sum;
        this.node = node;
    }
}