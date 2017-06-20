package company.facebook;

import chapter3.binaryTree.TreeNode;

/**
549. Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

Feel bad that you cant figure this out by yourself
 */
public class BinaryTreeLongestConsecutiveSequence2 {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    
    Result helper(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        Result left = helper(node.left);
        Result right = helper(node.right);
        
        Result now = new Result();
        now.node = node;
        now.inc = 1;
        now.dec = 1; // Leaf node, everything by itself
        
        if (left != null) {
            if (node.val - left.node.val == 1) { // increase
                now.inc = Math.max(now.inc, left.inc + 1);
            } else if (node.val - left.node.val == -1) { // decrease
                now.dec = Math.max(now.dec, left.dec + 1);
            }
        }
        
        if (right != null) {
            if (node.val - right.node.val == 1) { // increase
                now.inc = Math.max(now.inc, right.inc + 1);
            } else if (node.val - right.node.val == -1) { // decrease
                now.dec = Math.max(now.dec, right.dec + 1);
            }
        }
        
        max = Math.max(max, now.inc + now.dec - 1);
        
        return now;
    }
    
    class Result {
        TreeNode node;
        int inc;
        int dec;
    }
}
