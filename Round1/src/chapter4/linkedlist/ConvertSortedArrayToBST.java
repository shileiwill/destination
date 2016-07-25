package chapter4.linkedlist;

import chapter3.binaryTree.TreeNode;
/**
 * 108. Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * @author Lei
 *
 */
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    TreeNode helper(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        
        if (left > right) { // return null if invalid. we cant new again, because we did that already
            return null;
        }
        
        int mid = left + (right - left) / 2;
        
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        
        return root;
    }
}
