binaryTreeMaxpathSum.java


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        helper(root);

        return res;
    }

    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // if (root.left == null && root.right == null) {
        //     return root.val;
        // }

        int leftMax = helper(root.left);
        int rightMax = helper(root.right);
 
        int val1 = root.val;
        int val2 = root.val + leftMax;
        int val3 = root.val + rightMax;
        int val4 = root.val + leftMax + rightMax;
        System.out.println(val1 + ":" + val2 + ":" + val3 + ":" + val4 + ":");

        // global max, contains left child, right child and current node
        int global = Math.max(val1, Math.max(val2, Math.max(val3, val4)));
        // local max, with only one branch down
        int local = Math.max(val1, Math.max(val2, val3));
        res = Math.max(res, global);

        return local;
    }
}